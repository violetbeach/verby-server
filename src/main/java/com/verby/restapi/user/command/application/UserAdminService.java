package com.verby.restapi.user.command.application;

import com.verby.restapi.user.command.domain.*;
import com.verby.restapi.user.exception.LoginIdDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserAdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfo createAdmin(CreateAdminRequest request) {
        if(userRepository.existsByLoginId(request.getLoginId())) {
            throw new LoginIdDuplicateException(request.getLoginId());
        }

        HashSet<UserRole> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Role.MEMBER));
        roles.add(roleRepository.findByName(Role.ADMIN));

        User adminUser = new User(request.getLoginId(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                null,
                null,
                roles,
                false
        );

        userRepository.save(adminUser);

        return UserInfo.from(adminUser);
    }

}
