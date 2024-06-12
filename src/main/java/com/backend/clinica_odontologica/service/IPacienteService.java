package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;

import java.util.HashMap;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);
    HashMap<Long, PacienteSalidaDto> listarPacientes();
    PacienteSalidaDto buscarPacientePorId(Long id) ;
    public void eliminarPaciente(Long id) throws ResourceNotFoundException;
    PacienteSalidaDto modificarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id);
}
