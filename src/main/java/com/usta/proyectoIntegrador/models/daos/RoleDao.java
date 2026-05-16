package com.usta.proyectoIntegrador.models.daos;

import com.usta.proyectoIntegrador.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<RoleEntity, Long> {
}
