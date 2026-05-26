package com.usta.chilecomparte.services.interfaces;

import com.usta.chilecomparte.entities.RoleEntity;
import java.util.List;

public interface RoleService {
    public List<RoleEntity> findAll();
    public RoleEntity findById(Long id);
    public RoleEntity findByRol(String rol);
    public RoleEntity save(RoleEntity rol);
    public void deleteById(Long id);
}