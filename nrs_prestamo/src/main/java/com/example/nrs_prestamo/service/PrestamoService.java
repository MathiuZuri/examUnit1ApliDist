package com.example.nrs_prestamo.service;

import com.example.nrs_prestamo.entity.Prestamo;
import java.util.List;
import java.util.Optional;
import java.util.Set; // Importa Set si aún no lo has hecho

public interface PrestamoService {
    List<Prestamo> listar();
    Optional<Prestamo> listarPorId(Integer id);
    Prestamo guardar(Prestamo prestamo);
    Prestamo actualizar(Prestamo prestamo);
    void eliminarPorId(Integer id);

    // Agrega este método a tu interfaz PrestamoService
    void decrementarStockLibros(Integer libroId, Integer cantidad);

    // El resto de tus métodos de la interfaz...
}