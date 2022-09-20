package com.verby.restapi.account.query.dao;

import com.verby.restapi.account.query.dto.UserData;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserDataDao extends Repository<UserData, Long> {

    Optional<UserData> findById(long id);

}
