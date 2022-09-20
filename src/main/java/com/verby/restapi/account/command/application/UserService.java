package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.User;
import com.verby.restapi.account.command.domain.UserRepository;
import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void resetNickname(ResetNicknameRequest resetNicknameRequest) {
        User user = userRepository.findById(resetNicknameRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, "User not found."));

        user.rename(resetNicknameRequest.getName());
    }

}
