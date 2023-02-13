package com.verby.core.cover.query.dao;

import com.verby.core.cover.command.application.CoverSearchRequest;
import com.verby.core.cover.query.dto.CoverQueryModel;
import com.verby.core.util.pagination.CursorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class CustomCoverQueryDaoImpl implements CustomCoverQueryDao {

    private final MongoOperations operations;

    public List<CoverQueryModel> findAll(CoverSearchRequest request) {
        return operations.find(getQuery(request), CoverQueryModel.class);
    }

    private Query getQuery(CoverSearchRequest request) {
        CursorRequest cursor = request.getCursor();

        Query query = new Query();

        if(cursor.hasKey()) {
            query.addCriteria(where("id").lt(cursor.getKey()));
        }

        if(request.getContestId() != null) {
            query.addCriteria(where("contestId").is(request.getContestId()));
        }

        query.with(Sort.by(Sort.Direction.DESC, "id"));
        query.limit(cursor.getSize());
        return query;
    }


}
