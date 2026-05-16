package com.usta.proyectoIntegrador.models.services;

import com.usta.proyectoIntegrador.entities.UserEntity;
import com.usta.proyectoIntegrador.models.daos.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserEntity> ListAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserEntity user) {
        userDao.save(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}