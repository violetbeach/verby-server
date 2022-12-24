package verby.apiserver.inquiry.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import verby.apiserver.inquiry.command.domain.Inquiry;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreatedInquiryInfo {

    private final long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public static CreatedInquiryInfo from(Inquiry inquiry) {
        return new CreatedInquiryInfo(inquiry.getId(), inquiry.getTitle(), inquiry.getContent(), inquiry.getCreatedAt());
    }

}
