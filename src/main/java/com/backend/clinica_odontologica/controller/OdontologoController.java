package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public OdontologoSalidaDto registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto) {
        return odontologoService.registrarOdontologo(odontologoEntradaDto);
    }

    @GetMapping("/listar/todos")
    public HashMap<Long, OdontologoSalidaDto> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<OdontologoSalidaDto> modificarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto, @PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.modificarOdontologo(odontologoEntradaDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOdontologo(@RequestParam Long id) {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado exitosamente", HttpStatus.OK);
    }
}
