package com.pm.authservice.controller;

import com.pm.authservice.dto.Loginrequestdto;
import com.pm.authservice.dto.Loginresponsedto;
import com.pm.authservice.service.Authservice;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Authcontroller {
    private final Authservice authservice;
    public Authcontroller(Authservice authservice) {
        this.authservice = authservice;
    }
    @Operation(summary = "generate token or user")
    @PostMapping("/login")
    public ResponseEntity<Loginresponsedto> login(@RequestBody Loginrequestdto loginrequestdto) {
        Optional<String> tokenOptional = authservice.authenticate(loginrequestdto);
        if (tokenOptional.isPresent()) {
            String token = tokenOptional.get();
            return ResponseEntity.ok(new Loginresponsedto(token));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
