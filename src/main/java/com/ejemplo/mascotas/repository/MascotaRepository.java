package com.ejemplo.mascotas.repository;

import com.ejemplo.mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByEspecieIgnoreCase(String especie);
}
