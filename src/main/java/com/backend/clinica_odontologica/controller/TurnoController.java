package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("turnos")
@CrossOrigin
public class TurnoController {
    private ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto) {
        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);
        return ResponseEntity.ok(turnoSalidaDto);
    }

    @GetMapping("/listar/todos")
    public ResponseEntity<HashMap<Long, TurnoSalidaDto>> listarTurnos() {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<TurnoSalidaDto> modificarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto, @PathVariable Long id) {
        return new ResponseEntity<>(turnoService.modificarTurno(turnoEntradaDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarTurno(@RequestParam Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado exitosamente");
    }
}
