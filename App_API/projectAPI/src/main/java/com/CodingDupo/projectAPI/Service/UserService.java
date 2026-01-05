package com.CodingDupo.projectAPI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.CodingDupo.projectAPI.DTO.LoginDTO;
import com.CodingDupo.projectAPI.DTO.TokenDTO;
import com.CodingDupo.projectAPI.Exception.ResponseToException;
import com.CodingDupo.projectAPI.Model.RefreshToken;
import com.CodingDupo.projectAPI.Model.UserPrincipals;
import com.CodingDupo.projectAPI.Model.Users;
import com.CodingDupo.projectAPI.Repo.RefreshTokenRepo;
import com.CodingDupo.projectAPI.Repo.UserRepo;
import com.CodingDupo.projectAPI.Standards.Role;
import com.CodingDupo.projectAPI.Standards.Time;

@Service
public class UserService implements UserDetailsService {
    private UserRepo db;
    private JWTService jwtService;
    private RefreshTokenService rtService;
    private RefreshTokenRepo db2;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Autowired
    public UserService(UserRepo db,JWTService jwtService,RefreshTokenRepo db2,RefreshTokenService rtService){
        this.db=db;
        this.db2=db2;
        this.jwtService=jwtService;
        this.rtService=rtService;
    }

    public HttpStatus register(Users user) throws ResponseToException {
        if(db.findByUsername(user.getUsername())!=null) throw new ResponseToException("409:Username already exists");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.STUDENT);
        user.setFirstSeen(Time.now());
        user.setLastSeen(Time.now());
        db.save(user);
        return HttpStatus.CREATED;
    }

    public TokenDTO login(LoginDTO dto) throws ResponseToException {
        Users user = db.findByUsername(dto.getUsername());
        if(user == null) throw new ResponseToException("403:Username is not registered");
        if(!encoder.matches(dto.getPassword(), user.getPassword())) throw new ResponseToException("401:Password Mismatch");
        user.setLastSeen(Time.now());
        db.save(user);
        return generateToken(dto.getUsername(),user.getId());
    }
    private TokenDTO generateToken(String username,int id){
        String accessToken = jwtService.generateToken(username);
        String refreshToken = rtService.generateToken(username);
        db2.save(new RefreshToken(id,refreshToken));
        return new TokenDTO(accessToken, refreshToken);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = db.findByUsername(username);
        if(user==null) throw new UsernameNotFoundException("Username is not registered in our database");
        return new UserPrincipals(user);
    }
    public TokenDTO refresh(String refreshToken) throws ResponseToException {
        RefreshToken token = db2.findByToken(refreshToken);
        if(token==null || rtService.isTokenExpired(refreshToken)) throw new ResponseToException("400:Invalid Token");
        Users user = db.findById(token.getUserId()).get();
        if(user==null || !user.getUsername().equals(rtService.extractUserName(refreshToken))) throw new ResponseToException("401:Invalid User");
        return generateToken(user.getUsername(), user.getId());
    }
    public HttpStatus logout(UserDetails uds) throws ResponseToException{
        Users user = db.findByUsername(uds.getUsername());
        if(user==null) throw new ResponseToException("401:logout failed, Invalid User");
        if(db2.existsById(null)) throw new ResponseToException("400:No Such Sessions");
        db2.deleteById(user.getId());
        return HttpStatus.OK;
    }
}