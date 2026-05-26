package com.usta.chilecomparte.daos;

import com.usta.chilecomparte.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {

    @Query("SELECT u FROM UserEntity u JOIN FETCH u.rol WHERE u.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    UserEntity findByNombre(String nombre);
}