package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.PacienteRepository;
import com.backend.clinica_odontologica.service.IPacienteService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;

    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
        PacienteEntradaDto paciente = new PacienteEntradaDto(pacienteEntradaDto.getNombre(), pacienteEntradaDto.getApellido(), pacienteEntradaDto.getDni(), pacienteEntradaDto.getDomicilio());
        Paciente pacienteRecibido = modelMapper.map(paciente, Paciente.class);

        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteRepository.save(pacienteRecibido), PacienteSalidaDto.class);
        LOGGER.info("Paciente Registrado: " + JsonPrinter.toString(pacienteSalidaDto));

        return pacienteSalidaDto;
    }

    @Override
    public HashMap<Long, PacienteSalidaDto> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        HashMap<Long, PacienteSalidaDto> pacientesSalidaDto = new HashMap<>();

        pacientes.forEach((paciente) -> {
            PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);
            pacientesSalidaDto.put(pacienteSalidaDto.getId(), pacienteSalidaDto);
        });

        LOGGER.info("Listado de todos los pacientes: ", JsonPrinter.toString(pacientesSalidaDto));

        return pacientesSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);

        PacienteSalidaDto pacienteEncontrado = null;

        if(pacienteBuscado != null) {
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
            LOGGER.info("Paciente Encontrado: {}" + JsonPrinter.toString(pacienteEncontrado));
        } else LOGGER.error("No se ha encontrado el paciente con id {}", id);

        return pacienteEncontrado;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(buscarPacientePorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe registro del paciente con id " + id);
        }
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) {
        Paciente pacienteRecibido = modelMapper.map(pacienteEntradaDto, Paciente.class);
        Paciente pacienteAModificar = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;

        if(pacienteAModificar != null){
            pacienteRecibido.setId(pacienteAModificar.getId());
            pacienteRecibido.getDomicilio().setId(pacienteAModificar.getDomicilio().getId());
            pacienteAModificar = pacienteRecibido;

            //pacienteAModificar.setNombre(pacienteRecibido.getNombre());
            //pacienteAModificar.setApellido(pacienteRecibido.getApellido());
            //pacienteAModificar.setDni(pacienteRecibido.getDni());
            //pacienteAModificar.setFechaIngreso(pacienteRecibido.getFechaIngreso());
            //pacienteAModificar.getDomicilio().setCalle(pacienteRecibido.getDomicilio().getCalle());
            //pacienteAModificar.getDomicilio().setNumero(pacienteRecibido.getDomicilio().getNumero());
            //pacienteAModificar.getDomicilio().setLocalidad(pacienteRecibido.getDomicilio().getLocalidad());
            //pacienteAModificar.getDomicilio().setProvincia(pacienteRecibido.getDomicilio().getProvincia());

            pacienteRepository.save(pacienteAModificar);
            pacienteSalidaDto = modelMapper.map(pacienteAModificar, PacienteSalidaDto.class);
            LOGGER.warn("Paciente actualizado: ", JsonPrinter.toString(pacienteSalidaDto));
        } else {
            LOGGER.error("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
            //lanzar excepcion
        }

        return pacienteSalidaDto;
    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }
}