package com.verby.apiserver.user;

import com.verby.core.user.command.domain.UnavailableID;
import com.verby.core.user.command.domain.UnavailableIDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserPolicyController {

    private final UnavailableIDRepository unavailableIDRepository;

    @GetMapping("/unavailable-ids")
    public ResponseEntity<List<UnavailableID>> findAllUnavailableIds() {
        List<UnavailableID> unavailableIds = unavailableIDRepository.findAll();
        return new ResponseEntity<>(unavailableIds, HttpStatus.OK);
    }

}
