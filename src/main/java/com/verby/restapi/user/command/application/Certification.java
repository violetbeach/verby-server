package com.verby.restapi.user.command.application;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash("certification")
public class Certification {

    @Id
    String id;
    String phone;
    int certificationNUmber;

    public Certification(String phone, int certificationNUmber) {
        this.phone = phone;
        this.certificationNUmber = certificationNUmber;
    }
}
