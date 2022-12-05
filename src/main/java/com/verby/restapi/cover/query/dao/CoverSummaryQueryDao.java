package com.verby.restapi.cover.query.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.verby.restapi.cover.query.dto.CoverSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.verby.restapi.cover.query.dto.QCoverSummary.coverSummary;

@Repository
@RequiredArgsConstructor
public class CoverSummaryQueryDao {

    private final JPAQueryFactory queryFactory;

    public List<CoverSummary> noOffsetSearch(Long coverId, int pageSize) {
        return queryFactory
                .selectFrom(coverSummary)
                .where(
                        ltCoverId(coverId)
                )
                .orderBy(coverSummary.id.desc())
                .limit(pageSize)
                .fetch();
    }

    private BooleanExpression ltCoverId(Long coverId) {
        if (coverId == null) {
            return null;
        }
        return coverSummary.id.lt(coverId);
    }

}
