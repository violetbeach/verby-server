package com.verby.restapi.account.query.dao;

import com.verby.restapi.account.query.dto.AccountData;
import com.verby.restapi.account.query.dto.AccountLoginId;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AccountDataDao extends Repository<AccountData, Long> {

    Optional<AccountLoginId> findLoginIdByPhone(String phone);
    Optional<AccountData> findByLoginId(String loginId);

}
