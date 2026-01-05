package com.CodingDupo.projectAPI.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingDupo.projectAPI.Model.RefreshToken;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Integer>{
    RefreshToken findByToken(String token);
}
