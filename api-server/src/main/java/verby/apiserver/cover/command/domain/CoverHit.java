package verby.apiserver.cover.command.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import verby.apiserver.config.database.RedisHashKey;

import java.util.HashSet;
import java.util.Set;

@Getter
@RedisHash(value = RedisHashKey.COVER_HIT)
public class CoverHit {
    @Id
    private Long id;
    private Set<String> requestIPs = new HashSet<>();

    public CoverHit(Long id) {
        this.id = id;
    }

    public void hit(String requestIP) {
        requestIPs.add(requestIP);
    }
}
