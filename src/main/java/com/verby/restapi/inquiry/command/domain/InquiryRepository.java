package com.verby.restapi.inquiry.command.domain;

import org.springframework.data.repository.Repository;

public interface InquiryRepository extends Repository<Inquiry, Long> {

    Inquiry save(Inquiry inquiry);

}
