package verby.apiserver.inquiry.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.inquiry.command.domain.Inquiry;
import verby.apiserver.inquiry.command.domain.InquiryRepository;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    public CreatedInquiryInfo create(InquiryRequest request) {
        Inquiry inquiry = new Inquiry(request.getUserId(), request.getTitle(), request.getContent());
        inquiryRepository.save(inquiry);
        return CreatedInquiryInfo.from(inquiry);
    }
}
