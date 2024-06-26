package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.entity.Turno;
import com.backend.clinica_odontologica.exceptions.BadRequestException;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.OdontologoRepository;
import com.backend.clinica_odontologica.repository.PacienteRepository;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        Odontologo odontologo = odontologoRepository.findById(turnoEntradaDto.getOdontologo()).orElse(null);
        Paciente paciente = pacienteRepository.findById(turnoEntradaDto.getPaciente()).orElse(null);

        if (odontologo == null && paciente == null ) {
            throw new BadRequestException("Odontologo con id " + JsonPrinter.toString(turnoEntradaDto.getOdontologo()) + " y paciente con id " + JsonPrinter.toString(turnoEntradaDto.getPaciente()) + " no encontrados");
        } else if (odontologo == null) {
            throw new BadRequestException("Odontologo con id " + JsonPrinter.toString(turnoEntradaDto.getOdontologo()) + " no encontrado");
        } else if (paciente == null) {
            throw new BadRequestException("Paciente con id " + JsonPrinter.toString(turnoEntradaDto.getPaciente()) + " no encontrado");
        }

        Turno turno = modelMapper.map(turnoEntradaDto, Turno.class);
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRepository.save(turno), TurnoSalidaDto.class);
        LOGGER.info("Turno Registrado: {}", JsonPrinter.toString(turnoSalidaDto));

        return turnoSalidaDto;
    }


    @Override
    public HashMap<Long, TurnoSalidaDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();

        HashMap<Long, TurnoSalidaDto> turnosSalidaDto = new HashMap<>();

        turnos.forEach((turno) -> {
            TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
            turnosSalidaDto.put(turnoSalidaDto.getId(), turnoSalidaDto);
        });

        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnosSalidaDto));

        return turnosSalidaDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);

        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno Encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("No se ha encontrado el turno con id {}", id);

        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe registro del turno con id " + id);
        }
    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoRepository.findById(turnoEntradaDto.getOdontologo()).orElse(null);
        Paciente paciente = pacienteRepository.findById(turnoEntradaDto.getPaciente()).orElse(null);
        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoAModificar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAModificar != null) {
            turnoRecibido.setId(turnoAModificar.getId());
            turnoRecibido.setOdontologo(odontologo);
            turnoRecibido.setPaciente(paciente);

            turnoAModificar = turnoRecibido;

            turnoRepository.save(turnoAModificar);

            turnoSalidaDto = modelMapper.map(turnoAModificar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));
        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No existe turno con id " + id);
        }

        return turnoSalidaDto;
    }

    private void configureMapping() {
        modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                .addMappings(mapper -> mapper.skip(Turno::setOdontologo))
                .addMappings(mapper -> mapper.skip(Turno::setPaciente));

        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologo))
                .addMappings(mapper -> mapper.map(Turno::getPaciente, TurnoSalidaDto::setPaciente));
    }
}