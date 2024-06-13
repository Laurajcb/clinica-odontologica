package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;

import java.util.HashMap;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException;
    HashMap<Long, TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto buscarTurnoPorId(Long id);
    void eliminarTurno(Long id) throws ResourceNotFoundException;
    TurnoSalidaDto modificarTurno(TurnoEntradaDto turnoEntradaDto, Long id);
}
