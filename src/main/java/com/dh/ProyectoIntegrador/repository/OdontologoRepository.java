package com.dh.ProyectoIntegrador.repository;

import com.dh.ProyectoIntegrador.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface OdontologoRepository  extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findById(Long Id);

}
