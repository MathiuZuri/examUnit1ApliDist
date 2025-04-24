package com.example.nrs_prestamo.controller;

import com.example.nrs_prestamo.entity.Prestamo;
import com.example.nrs_prestamo.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping()
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        return new ResponseEntity<>(prestamoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOptional = prestamoService.listarPorId(id);
        return prestamoOptional.map(prestamo -> new ResponseEntity<>(prestamo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<?> guardarPrestamo(@RequestBody Prestamo prestamo) {
        try {
            Prestamo prestamoGuardado = prestamoService.guardar(prestamo);
            return new ResponseEntity<>(prestamoGuardado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        Optional<Prestamo> prestamoExistenteOptional = prestamoService.listarPorId(id);
        if (prestamoExistenteOptional.isPresent()) {
            prestamo.setId(id); // Asegurar que el ID sea el correcto para la actualización
            return new ResponseEntity<>(prestamoService.actualizar(prestamo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el préstamo con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOptional = prestamoService.listarPorId(id);
        if (prestamoOptional.isPresent()) {
            prestamoService.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Puedes agregar endpoints específicos para funcionalidades como:
    // - Devolver un libro (actualizar fechaDevuelto y estado)
    // - Listar préstamos activos por usuario
    // - Listar préstamos vencidos

    // Ejemplo: Endpoint para devolver un préstamo
    @PutMapping("/{id}/devolver")
    public ResponseEntity<?> devolverPrestamo(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOptional = prestamoService.listarPorId(id);
        if (prestamoOptional.isPresent()) {
            Prestamo prestamo = prestamoOptional.get();
            if (prestamo.getFechaDevuelto() == null) {
                prestamo.setFechaDevuelto(LocalDate.now());
                prestamo.setEstado("devuelto");
                return new ResponseEntity<>(prestamoService.actualizar(prestamo), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El préstamo con ID: " + id + " ya ha sido devuelto.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("No se encontró el préstamo con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // Ejemplo: Endpoint para listar préstamos activos por usuario
    @GetMapping("/usuario/{usuarioId}/activos")
    public ResponseEntity<List<Prestamo>> listarPrestamosActivosPorUsuario(@PathVariable Integer usuarioId) {
        // Asumiendo que tienes un método en el service para esto
        // return new ResponseEntity<>(prestamoService.listarPrestamosActivosPorUsuario(usuarioId), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    // Ejemplo: Endpoint para listar préstamos vencidos
    @GetMapping("/vencidos")
    public ResponseEntity<List<Prestamo>> listarPrestamosVencidos() {
        // Asumiendo que tienes un método en el service para esto
        // return new ResponseEntity<>(prestamoService.listarPrestamosVencidos(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}