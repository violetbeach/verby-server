package com.verby.restapi.cover.query.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.verby.restapi.common.util.pagination.CursorRequest;
import com.verby.restapi.cover.command.application.CoverSearchRequest;
import com.verby.restapi.cover.query.dto.CoverDetailQueryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.verby.restapi.cover.query.dto.QCoverDetailQueryModel.coverDetailQueryModel;

@Repository
@RequiredArgsConstructor
public class CoverDetailQueryDao {

    private final JPAQueryFactory queryFactory;

    public List<CoverDetailQueryModel> findAll(CoverSearchRequest request) {
        CursorRequest cursor = request.getCursor();

        return queryFactory
                .selectFrom(coverDetailQueryModel)
                .where(
                        coverIdLt(cursor.getKey()),
                        contestIdEq(request.getContestId())
                )
                .orderBy(coverDetailQueryModel.id.desc())
                .limit(cursor.getSize())
                .fetch();
    }

    private BooleanExpression coverIdLt(Long coverId) {
        if (coverId == null) {
            return null;
        }
        return coverDetailQueryModel.id.lt(coverId);
    }

    private BooleanExpression contestIdEq(Long contestId) {
        if (contestId == null) {
            return null;
        }
        return coverDetailQueryModel.contestId.eq(contestId);
    }

}
