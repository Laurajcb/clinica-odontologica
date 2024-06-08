package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("turnos")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public TurnoSalidaDto registrarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto) {
        return turnoService.registrarTurno(turnoEntradaDto);
    }

    @GetMapping("/listar/todos")
    public HashMap<Long, TurnoSalidaDto> listarTurnos() {
        return turnoService.listarTurnos();
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
    public ResponseEntity<?> eliminarTurno(@RequestParam Long id) {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado exitosamente", HttpStatus.OK);
    }
}
