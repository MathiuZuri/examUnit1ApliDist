package com.example.nrs_prestamo.repository;

import com.example.nrs_prestamo.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    int countByUsuarioIdAndEstado(Integer usuarioId, String estado);
}