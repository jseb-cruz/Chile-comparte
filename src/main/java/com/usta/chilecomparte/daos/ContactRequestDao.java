package com.usta.chilecomparte.daos;

import com.usta.chilecomparte.entities.ContactRequestEntity;
import com.usta.chilecomparte.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRequestDao extends JpaRepository<ContactRequestEntity, Long> {

    Page<ContactRequestEntity> findByFinalidad(String finalidad, Pageable pageable);

    List<ContactRequestEntity> findByEmailOrderByFechaDesc(String email);

    @Repository
    public interface ContactRequestRepository extends JpaRepository<ContactRequestEntity, Long> {

        List<ContactRequestEntity> findByEmailOrderByFechaDesc(String email);

    }

}