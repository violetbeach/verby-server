package verby.apiserver.inquiry.query.dao;

import org.springframework.data.repository.Repository;
import verby.apiserver.inquiry.query.dto.InquiryData;

import java.util.List;

public interface InquiryDataDao extends Repository<InquiryData, Long> {
    List<InquiryData> findByInquirerId(long inquirerId);
}
