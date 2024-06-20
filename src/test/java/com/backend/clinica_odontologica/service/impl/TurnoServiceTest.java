package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;

    private Long odontologoId;

    private Long pacienteId;

    @BeforeEach
    void setUpEach() {
        //Crea, guarda un odontologo si no existe
        if (odontologoId == null) {
            OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("RM-456789", "Juan", "Perez");

            OdontologoSalidaDto odontologoGuardado = odontologoService.registrarOdontologo(odontologoEntradaDto);
            assertNotNull(odontologoGuardado.getId());
            odontologoId = odontologoGuardado.getId();
        }

        //Crea, guarda un paciente si no existe
        if (pacienteId == null) {
            DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Calle", 345, "Puente", "Aranda");
            PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Maria", "Perez", 12345, domicilioEntradaDto);

            PacienteSalidaDto pacienteGuardado = pacienteService.registrarPaciente(pacienteEntradaDto);
            assertNotNull(pacienteGuardado.getId());
            pacienteId = pacienteGuardado.getId();
        }
    }

    @Test
    @Order(1)
    void deberiaGuardarUnTurnoYRetornarSuId() {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.now(), odontologoId, pacienteId);

        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        assertNotNull(turnoSalidaDto);
        assertNotNull(turnoSalidaDto.getId());
        assertEquals(odontologoId, turnoSalidaDto.getOdontologo().getId());
        assertEquals(pacienteId, turnoSalidaDto.getPaciente().getId());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeTurnos() {
        HashMap<Long, TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();

        assertFalse(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElTurnoConId1() {
        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDeTurnos() {
        HashMap<Long, TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();

        assertTrue(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(5)
    void deberiaLanzarResourceNotFoundExceptionAlEliminarTurnoInexistente() {
        Long idNoExistente = 999L;

        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(idNoExistente));
    }

    @Test
    @Order(6)
    void deberiaLanzarResourceNotFoundExceptionAlModificarTurnoInexistente() {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.now(), odontologoId, pacienteId);
        Long idTurno = 1L;

        assertThrows(ResourceNotFoundException.class, () -> turnoService.modificarTurno(turnoEntradaDto, idTurno));
    }
}
