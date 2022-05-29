package com.comicall.ComicallApi.repositories;

import com.comicall.ComicallApi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r where r.name = ?1")
    Role findByName(String name);
    boolean existsByName(String name);
}