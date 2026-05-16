package com.usta.proyectoIntegrador.models.services;

import com.usta.proyectoIntegrador.entities.RoleEntity;

import java.util.List;

public interface RoleService {
    public List<RoleEntity> ListAll();
    public RoleEntity findById(Long id);
    public void save(RoleEntity role);
    void delete(Long id);
}
