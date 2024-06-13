package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.OdontologoRepository;
import com.backend.clinica_odontologica.service.IOdontologoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private OdontologoRepository odontologoRepository;

    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);

        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoRepository.save(odontologo), OdontologoSalidaDto.class);
        LOGGER.info("Odontologo Registrado: {}" + JsonPrinter.toString(odontologoSalidaDto));

        return odontologoSalidaDto;
    }

    @Override
    public HashMap<Long, OdontologoSalidaDto> listarOdontologos() {
        List<Odontologo> ondontologos = odontologoRepository.findAll();

        HashMap<Long, OdontologoSalidaDto> odontologosSalidaDto = new HashMap<>();

        ondontologos.forEach((odontologo) -> {
            OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologo, OdontologoSalidaDto.class);
            odontologosSalidaDto.put(odontologoSalidaDto.getId(), odontologoSalidaDto);
        });

        LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologosSalidaDto));

        return odontologosSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        OdontologoSalidaDto odontologoEncontrado = null;

        if(odontologoBuscado != null) {
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo Encontrado: {}" + JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("No se ha encontrado el odontologo con id {}", id);

        return odontologoEncontrado;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException{
        if(buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id {}", id);
        }else {
            throw new ResourceNotFoundException("No existe registro de odontologo con id " + id);
        }
    }

    @Override
    public OdontologoSalidaDto modificarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) {
        Odontologo odontologoRecibido = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        Odontologo odontologoAModificar = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if(odontologoAModificar != null) {
            odontologoRecibido.setId(odontologoAModificar.getId());
            odontologoAModificar = odontologoRecibido;

            odontologoRepository.save(odontologoAModificar);

            odontologoSalidaDto = modelMapper.map(odontologoAModificar, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoSalidaDto));
        } else {
            LOGGER.error("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
            //lanzar excepcion
        }

        return odontologoSalidaDto ;
    }
}