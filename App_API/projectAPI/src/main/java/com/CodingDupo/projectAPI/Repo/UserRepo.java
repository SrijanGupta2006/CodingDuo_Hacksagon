package com.CodingDupo.projectAPI.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CodingDupo.projectAPI.Model.Users;


@Repository
public interface UserRepo extends JpaRepository<Users,Integer>{
    Users findByUsername(String username);
}