package com.verby.restapi.user.command.application;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.user.command.domain.*;
import com.verby.restapi.user.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAuthService {

    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UnavailableIDRepository unavailableIDRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public void sendCertificationSMS(SendCertificationSMSRequest request) {
        Certification certification = new Certification(request.getPhone(), generateCertificationNumber());
        certificationRepository.save(certification);
    }

    public VerificationToken createToken(SMSCertificationRequest request) {
        String phone = request.getPhone();
        verifyCertificationRequest(phone, request.getCertificationNumber());

        VerificationToken verificationToken = new VerificationToken(phone);
        verificationTokenRepository.save(verificationToken);

        return verificationToken;
    }

    public UserLoginId findLoginId(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByKey(token)
                .orElseThrow(() -> new TokenNotFoundException(token));
        verifyToken(verificationToken);

        User user = userRepository.findByPhone(verificationToken.getPhone())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, "Not found."));

        return new UserLoginId(user.getId(), user.getLoginId(), user.getCreatedAt());
    }

    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        VerificationToken verificationToken = verificationTokenRepository.findByKey(request.getToken())
                .orElseThrow(() -> new TokenNotFoundException(request.getToken()));
        verifyToken(verificationToken);

        User user = userRepository.findByPhone(verificationToken.getPhone())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, "Not found"));

        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        user.resetPassword(encodedPassword);
    }

    public CreatedUserInfo signUp(SignUpRequest request) {
        VerificationToken verificationToken = verificationTokenRepository.findByKey(request.getToken())
                .orElseThrow(() -> new TokenNotFoundException(request.getToken()));
        verifyToken(verificationToken);

        if(!verificationToken.getPhone().equals(request.getPhone())) {
            throw new InvalidTokenException("Verification token is different from input.");
        }

        verifyUniqueLoginId(request.getLoginId());
        verifyUniquePhoneNumber(request.getPhone());
        verifyAvailableLoginId(request);

        UserRole role = roleRepository.findByName(Role.MEMBER);

        User user = new User(request.getLoginId(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getPhone(),
                request.getGender(),
                new HashSet<>(List.of(role)),
                request.getAllowToMarketingNotification()
        );

        userRepository.save(user);

        return CreatedUserInfo.from(user);
    }

    private void verifyAvailableLoginId(SignUpRequest request) {
        List<UnavailableID> unavailableIds = unavailableIDRepository.findAll();
        boolean match = unavailableIds.stream()
                .anyMatch(u -> u.getLoginId().equals(request.getLoginId()));
        if(match) {
            throw new UnavailableLoginIdException(request.getLoginId());
        }
    }

    private void verifyToken(VerificationToken verificationToken) {
        verificationToken.verifyExpirationDate();
        verificationTokenRepository.delete(verificationToken);
    }

    private void verifyUniqueLoginId(String loginId) {
        if(userRepository.existsByLoginId(loginId)) {
            throw new LoginIdDuplicateException(loginId);
        }
    }

    private void verifyUniquePhoneNumber(String phoneNumber) {
        if(userRepository.existsByPhone(phoneNumber)) {
            throw new PhoneNumberDuplicateException(phoneNumber);
        }
    }

    // TODO
    private int generateCertificationNumber() {
        return 1111;
    }

    private void verifyCertificationRequest(String phone, int certificationNumber) {
        Optional<Certification> result = certificationRepository.findByPhone(phone);

        if(result.isEmpty() || result.get().getCertificationNumber() != certificationNumber) {
            throw new InvalidCertificationNumberException(certificationNumber);
        }

        certificationRepository.delete(result.get());
    }

}
