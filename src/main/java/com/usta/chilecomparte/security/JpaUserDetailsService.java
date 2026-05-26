package com.usta.chilecomparte.security;

import com.usta.chilecomparte.daos.UserDao;
import com.usta.chilecomparte.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscamos el usuario (el UserDao ya trae el rol cargado gracias al JOIN FETCH)
        UserEntity user = userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        // Definimos las autoridades
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Si el rol existe, lo agregamos (asumiendo que user.getRol().getRol() devuelve el nombre del rol)
        if (user.getRol() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRol().getRol()));
        }

        // Retornamos el objeto User de Spring Security
        return new User(user.getEmail(), user.getClave(), authorities);
    }
}