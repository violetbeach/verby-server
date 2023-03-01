package com.verby.internalconsumerserver.cover.infra.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@Immutable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Subselect(
        """
        SELECT cl.id as id,
            cl.cover_id as cover_id,
            cl.user_id as user_id
        FROM cover_like cl
        LEFT OUTER JOIN cover c
            ON c.id = cl.cover_id
        """)
@Synchronize({ "cover_like", "cover" })
public class CoverLike {
    @Id
    private long id;
    private long coverId;
    private long userId;

}
