package com.example.nrs_prestamo.controller;

import com.example.nrs_prestamo.entity.Prestamo;
import com.example.nrs_prestamo.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @Autowired
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        List<Prestamo> prestamos = prestamoService.getAllPrestamos();
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOptional = prestamoService.getPrestamoById(id);
        return prestamoOptional.map(prestamo -> {
                    String nombreUsuario = prestamoService.obtenerNombreUsuario(prestamo.getUsuarioId());
                    prestamo.setUsuarioNombre(nombreUsuario);
                    return new ResponseEntity<>(prestamo, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody Prestamo prestamo) {
        prestamoService.procesarPrestamo(prestamo); // Usamos el método del servicio que interactúa con libro y usuario
        return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamoActualizado) {
        Optional<Prestamo> prestamoExistente = prestamoService.getPrestamoById(id);
        if (prestamoExistente.isPresent()) {
            prestamoActualizado.setId(id);
            Prestamo prestamoGuardado = prestamoService.savePrestamo(prestamoActualizado);
            return new ResponseEntity<>(prestamoGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Integer id) {
        if (prestamoService.getPrestamoById(id).isPresent()) {
            prestamoService.deletePrestamo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}/devolver")
    public ResponseEntity<Void> devolverPrestamo(@PathVariable Integer id) {
        if (prestamoService.getPrestamoById(id).isPresent()) {
            prestamoService.procesarDevolucion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<String> obtenerNombreUsuario(@PathVariable Integer usuarioId) {
        // Corregido el nombre del path variable para consistencia
        // y alineado con la función del servicio
        String nombre = prestamoService.obtenerNombreUsuario(usuarioId);
        if (nombre != null) {
            return new ResponseEntity<>(nombre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/libros/{libroId}/titulo")
    public ResponseEntity<String> obtenerTituloLibro(@PathVariable Integer libroId) {
        // Corregido el nombre del path variable para consistencia
        String titulo = prestamoService.obtenerTituloLibro(libroId);
        if (titulo != null) {
            return new ResponseEntity<>(titulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/con-usuario")
    public ResponseEntity<Prestamo> obtenerPrestamoConUsuario(@PathVariable Integer id) {
        // Renombrado para mayor claridad y consistencia con las convenciones REST
        Optional<Prestamo> prestamoOptional = prestamoService.obtenerPrestamoConUsuarioPorId(id);
        return prestamoOptional.map(prestamo -> new ResponseEntity<>(prestamo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}