package com.usta.proyectoIntegrador.models.services;


import com.usta.proyectoIntegrador.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserEntity> ListAll();
    public void save(UserEntity user);
    Optional <UserEntity> findByEmail(String email);

}