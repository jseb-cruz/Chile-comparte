package com.usta.chilecomparte.daos;

import com.usta.chilecomparte.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRol(String rol);
}