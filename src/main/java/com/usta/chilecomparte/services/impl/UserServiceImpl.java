package com.usta.chilecomparte.services.impl;

import com.usta.chilecomparte.daos.UserDao;
import com.usta.chilecomparte.entities.UserEntity;
import com.usta.chilecomparte.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(String id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity usuario) {
        return userDao.save(usuario);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    @Override
    public UserEntity findByNombre(String nombre) {

        return userDao.findByNombre(nombre);
    }
}