package com.verby.restapi.user.query.application;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.user.query.dao.UserDataDao;
import com.verby.restapi.user.query.dto.UserLoginId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserQueryService {

    private final UserDataDao userDataDao;

    public UserLoginId findLoginIdByPhoneAuth(String phone) {
        return userDataDao.findLoginIdByPhone(phone)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, "Not found."));
    }

}
