package verby.apiserver.user.command.application;

import lombok.Getter;

@Getter
public class SendResetPasswordSMSRequest extends SendCertificationSMSRequest {

    private final String loginId;

    public SendResetPasswordSMSRequest(String phone, String loginId) {
        super(phone);
        this.loginId = loginId;
    }
}
