package com.verby.restapi.account.query.application;

import com.verby.restapi.account.query.dao.AccountDataDao;
import com.verby.restapi.account.query.dto.AccountLoginId;
import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountQueryService {

    private final AccountDataDao accountDataDao;

    public AccountLoginId findLoginIdByPhoneAuth(String phone) {
        return accountDataDao.findLoginIdByPhone(phone)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND, "Not found."));
    }

}
