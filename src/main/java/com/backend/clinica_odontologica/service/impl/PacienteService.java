package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.service.IPacienteService;

import java.util.List;

public class PacienteService implements IPacienteService {
    @Override
    public Paciente registrarPaciente (PacienteEntradaDto pacienteEntradaDto){
        return null;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        return null;
    }

}