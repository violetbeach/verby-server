package com.verby.restapi.inquiry.presentation;


import com.verby.restapi.config.security.SecurityUser;
import com.verby.restapi.inquiry.command.application.CreatedInquiryInfo;
import com.verby.restapi.inquiry.command.application.InquiryRequest;
import com.verby.restapi.inquiry.command.application.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    private ResponseEntity<CreatedInquiryInfo> create(@AuthenticationPrincipal SecurityUser user, @RequestBody @Valid InquiryRequest request) {
        request.setUserId(user.getUserId());
        CreatedInquiryInfo inquiry = inquiryService.create(request);
        return new ResponseEntity<>(inquiry, HttpStatus.CREATED);
    }

}
