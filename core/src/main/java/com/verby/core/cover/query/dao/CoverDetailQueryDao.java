package com.verby.core.cover.query.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.verby.apiserver.common.util.pagination.CursorRequest;
import com.verby.core.cover.command.application.CoverSearchRequest;
import com.verby.core.cover.query.dto.CoverDetailQueryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.verby.apiserver.cover.query.dto.QCoverDetailQueryModel.coverDetailQueryModel;

@Repository
@RequiredArgsConstructor
public class CoverDetailQueryDao {

    private final JPAQueryFactory queryFactory;

    public List<CoverDetailQueryModel> findAll(CoverSearchRequest request) {
        CursorRequest cursor = request.getCursor();

        return queryFactory
                .selectFrom(QCoverDetailQueryModel.coverDetailQueryModel)
                .where(
                        coverIdLt(cursor.getKey()),
                        contestIdEq(request.getContestId())
                )
                .orderBy(QCoverDetailQueryModel.coverDetailQueryModel.id.desc())
                .limit(cursor.getSize())
                .fetch();
    }

    private BooleanExpression coverIdLt(Long coverId) {
        if (coverId == null) {
            return null;
        }
        return QCoverDetailQueryModel.coverDetailQueryModel.id.lt(coverId);
    }

    private BooleanExpression contestIdEq(Long contestId) {
        if (contestId == null) {
            return null;
        }
        return QCoverDetailQueryModel.coverDetailQueryModel.contestId.eq(contestId);
    }

}
