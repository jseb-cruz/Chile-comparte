package com.usta.chilecomparte.services.interfaces;

import com.usta.chilecomparte.entities.UserEntity;
import java.util.List;

public interface UserService {
    public List<UserEntity> findAll();
    public UserEntity findById(String id);
    public UserEntity findByEmail(String email);
    public UserEntity save(UserEntity usuario);
    public void deleteById(String id);
    public UserEntity findByNombre(String nombre);
}