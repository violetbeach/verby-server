package com.verby.apiserver.cover.presentation;

import com.verby.apiserver.common.util.ip.ClientIPUtils;
import com.verby.apiserver.cover.command.application.CoverHitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/covers")
@RequiredArgsConstructor
public class CoverHitController {

    private final CoverHitService coverHitService;

    @PostMapping("/{coverId}/hits")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void hit(@PathVariable Long coverId, HttpServletRequest request) {
        String clientIP = ClientIPUtils.getRemoteIP(request);
        coverHitService.hit(coverId, clientIP);
    }

}
