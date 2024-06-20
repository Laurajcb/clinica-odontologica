package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("odontologos")
@CrossOrigin
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto) {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologoEntradaDto), HttpStatus.CREATED);
    }

    @GetMapping("/listar/todos")
    public ResponseEntity<HashMap<Long, OdontologoSalidaDto>> listarOdontologos() {
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
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
    public ResponseEntity<?> eliminarOdontologo(@RequestParam Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado exitosamente");
    }
}
