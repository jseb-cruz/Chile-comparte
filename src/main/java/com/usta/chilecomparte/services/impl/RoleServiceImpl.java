package com.usta.chilecomparte.services.impl;

import com.usta.chilecomparte.daos.RoleDao;
import com.usta.chilecomparte.entities.RoleEntity;
import com.usta.chilecomparte.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public List<RoleEntity> findAll() {
        return roleDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleEntity findById(Long id) {
        return roleDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleEntity findByRol(String rol) {
        return roleDao.findByRol(rol).orElse(null);
    }

    @Override
    @Transactional
    public RoleEntity save(RoleEntity rol) {
        return roleDao.save(rol);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }
}