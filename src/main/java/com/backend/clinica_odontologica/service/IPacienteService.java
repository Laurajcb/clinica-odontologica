package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;

import java.util.HashMap;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);
    HashMap<Long, PacienteSalidaDto> listarPacientes();
    PacienteSalidaDto buscarPacientePorId(Long id);
    void eliminarPaciente(Long id);
    PacienteSalidaDto modificarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id);
}
