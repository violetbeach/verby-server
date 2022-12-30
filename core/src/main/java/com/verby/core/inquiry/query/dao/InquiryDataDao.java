package com.verby.core.inquiry.query.dao;

import com.verby.core.inquiry.query.dto.InquiryData;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface InquiryDataDao extends Repository<InquiryData, Long> {
    List<InquiryData> findByInquirerId(long inquirerId);
}
