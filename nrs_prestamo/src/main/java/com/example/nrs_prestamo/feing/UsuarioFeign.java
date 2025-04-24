package com.example.nrs_prestamo.feing;

import com.example.nrs_prestamo.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nrs-usuario-service", path = "/usuarios") // El nombre del servicio de usuario
public interface UsuarioFeign {

    @GetMapping("/{id}/estado")
    ResponseEntity<String> obtenerEstadoUsuario(@PathVariable Integer id);

    @GetMapping("/{id}")
    ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable Integer id);
}