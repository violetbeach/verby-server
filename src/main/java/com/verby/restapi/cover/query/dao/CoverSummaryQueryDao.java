package com.verby.restapi.cover.query.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.verby.restapi.cover.command.application.CoverSearchRequest;
import com.verby.restapi.external.cover.infra.dto.CoverSummary;
import com.verby.restapi.external.cover.infra.dto.QCoverSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.verby.restapi.external.cover.infra.dto.QCoverSummary.coverSummary;

@Repository
@RequiredArgsConstructor
public class CoverSummaryQueryDao {

    private final JPAQueryFactory queryFactory;

    public List<CoverSummary> findAll(CoverSearchRequest request) {
        return queryFactory
                .selectFrom(coverSummary)
                .where(
                        coverIdLt(request.getCoverIdLt()),
                        contestIdEq(request.getContestId())
                )
                .orderBy(coverSummary.id.desc())
                .limit(request.getPageSize())
                .fetch();
    }

    public Optional<CoverSummary> findById(Long coverId) {
        CoverSummary coverSummary = queryFactory
                .selectFrom(QCoverSummary.coverSummary)
                .where(
                        coverIdEq(coverId)
                )
                .fetchOne();
        return Optional.ofNullable(coverSummary);
    }

    private BooleanExpression coverIdEq(Long coverId) {
        if (coverId == null) {
            return null;
        }
        return coverSummary.id.eq(coverId);
    }

    private BooleanExpression coverIdLt(Long coverId) {
        if (coverId == null) {
            return null;
        }
        return coverSummary.id.lt(coverId);
    }

    private BooleanExpression contestIdEq(Long contestId) {
        if (contestId == null) {
            return null;
        }
        return coverSummary.contestId.eq(contestId);
    }

}
