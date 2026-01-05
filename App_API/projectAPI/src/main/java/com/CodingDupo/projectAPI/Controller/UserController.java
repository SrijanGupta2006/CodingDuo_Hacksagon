package com.CodingDupo.projectAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingDupo.projectAPI.DTO.LoginDTO;
import com.CodingDupo.projectAPI.DTO.RefreshTokenDTO;
import com.CodingDupo.projectAPI.Exception.ResponseToException;
import com.CodingDupo.projectAPI.Model.Users;
import com.CodingDupo.projectAPI.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;
    @Autowired
    public UserController(UserService service){
        this.service=service;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) throws ResponseToException{
        return new ResponseEntity<>(service.register(user));
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO user) throws ResponseToException{
        return new ResponseEntity<>(service.login(user),HttpStatus.OK);
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshUser(@RequestBody RefreshTokenDTO dto) throws ResponseToException{
        return new ResponseEntity<>(service.refresh(dto.getRefreshToken()),HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@AuthenticationPrincipal UserDetails uds) throws ResponseToException{
        return new ResponseEntity<>(service.logout(uds));
    }
}
