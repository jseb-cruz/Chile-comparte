package com.usta.proyectoIntegrador.models.services;

import com.usta.proyectoIntegrador.entities.RoleEntity;
import com.usta.proyectoIntegrador.models.daos.RoleDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    public final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleEntity> ListAll() {
        return roleDao.findAll();
    }

    @Override
    public RoleEntity findById(Long id) {
        return roleDao.findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("Role not found"));
    }

    @Override
    public void save(RoleEntity role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Long id) {
        roleDao.deleteById(id);
    }
}