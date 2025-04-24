package com.example.nrs_prestamo.repository;

import com.example.nrs_prestamo.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    int countByUsuarioIdAndEstado(Integer usuarioId, String estado);

    List<Prestamo> findByUsuarioId(Integer usuarioId);

    List<Prestamo> findByEstado(String estado);

    List<Prestamo> findByFechaDevolucionBeforeAndFechaDevueltoIsNull(LocalDate fechaDevolucion);

    List<Prestamo> findByUsuarioIdAndEstado(Integer usuarioId, String estado);
}