package com.verby.apiserver.user.query.dao;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.verby.apiserver.user.query.dto.UserData;
import com.verby.apiserver.user.query.dto.UserSearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.verby.apiserver.user.query.dto.QUserData.userData;

@Repository
@RequiredArgsConstructor
public class UserDataQueryDao {

    private final JPAQueryFactory queryFactory;

    public Optional<UserData> searchOne(UserSearchParam userSearchParam) {
        return Optional.ofNullable(queryFactory
                .selectFrom(userData)
                .where(loginIdEq(userSearchParam.getLoginId()),
                        phoneEq(userSearchParam.getPhone()))
                .fetchOne());
    }

    private BooleanExpression loginIdEq(String loginIdCond) {
        if (loginIdCond == null) {
            return null;
        }
        return userData.loginId.eq(loginIdCond);
    }

    private BooleanExpression phoneEq(String phoneCond) {
        if (phoneCond == null) {
            return null;
        }
        return userData.phone.eq(phoneCond);
    }

}
