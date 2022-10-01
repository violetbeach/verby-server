package com.verby.restapi.user.command.application;

import com.verby.restapi.user.command.domain.*;
import com.verby.restapi.user.exception.LoginIdDuplicateException;
import com.verby.restapi.user.exception.PhoneNumberDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfo signUp(SignUpRequest request) {
        verifyUniqueLoginId(request.getLoginId());
        verifyUniquePhoneNumber(request.getPhone());

        UserRole role = roleRepository.findByName(Role.MEMBER);

        User user = new User(request.getLoginId(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getPhone(),
                new HashSet<>(List.of(role)),
                request.getAllowToMarketingNotification()
        );

        userRepository.save(user);

        return UserInfo.from(user);
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

}
