package verby.apiserver.cover.query.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import verby.apiserver.common.util.pagination.CursorRequest;
import verby.apiserver.cover.command.application.CoverSearchRequest;
import verby.apiserver.cover.query.dto.CoverDetailQueryModel;

import java.util.List;

import static verby.apiserver.cover.query.dto.QCoverDetailQueryModel.coverDetailQueryModel;

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
