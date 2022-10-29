package com.verby.restapi.user.query.dao;

import com.verby.restapi.user.query.dto.UserData;
import com.verby.restapi.user.query.dto.UserSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserDataDao extends Repository<UserData, Long> {

    @Query("SELECT new com.verby.restapi.user.query.dto.UserSummary(u.id, u.loginId, u.bio, u.profileImage) FROM User u WHERE u.id = :id")
    Optional<UserSummary> findSummaryById(long id);

}
