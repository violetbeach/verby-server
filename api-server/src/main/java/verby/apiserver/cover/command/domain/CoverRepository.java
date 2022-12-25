package verby.apiserver.cover.command.domain;

import org.springframework.data.repository.Repository;
import verby.core.cover.Cover;

public interface CoverRepository extends Repository<Cover, Long> {

    Cover save(Cover cover);

}
