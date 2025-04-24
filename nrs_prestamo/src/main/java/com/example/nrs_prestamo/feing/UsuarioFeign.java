package com.example.nrs_prestamo.feing;

import com.example.nrs_prestamo.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nrs-usuario-service") // El nombre del servicio de usuario
public interface UsuarioFeign {

    @GetMapping("/usuarios/{id}/estado")
    ResponseEntity<String> obtenerEstadoUsuario(@PathVariable Integer id);

    @GetMapping("/usuarios/{id}")
    ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable Integer id); // Nuevo método para obtener la info del usuario
}