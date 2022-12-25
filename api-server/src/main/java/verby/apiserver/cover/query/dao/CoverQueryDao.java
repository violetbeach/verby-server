package verby.apiserver.cover.query.dao;

import org.springframework.data.repository.Repository;
import verby.apiserver.cover.query.dto.CoverQueryModel;

import java.util.Optional;

public interface CoverQueryDao extends Repository<CoverQueryModel, Long> {

    Optional<CoverQueryModel> findById(long id);

}
