package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@RunWith(SpringJUnit4ClassRunner.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarseUnPacienteDeNombreMaria_yRetornarSuId() {
        DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Calle", 123, "Sol", "Pool");

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Maria", "Perez", 12345, domicilioEntradaDto);

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        // assert
        assertNotNull(pacienteSalidaDto);
        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Maria", pacienteSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDePacientes() {
        HashMap<Long, PacienteSalidaDto> listadoDePacientes = pacienteService.listarPacientes();
        assertFalse(listadoDePacientes.isEmpty());
    }
    @Test
    @Order(3)
    void deberiaEliminarseElPacienteConId1() {
        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDePacientes() {
        HashMap<Long, PacienteSalidaDto> listadoDePacientes = pacienteService.listarPacientes();
        assertTrue(listadoDePacientes.isEmpty());
    }

    @Test
    @Order(5)
    void deberiaLanzarResourceNotFoundExceptionSiPacienteNoExiste() {
        Long idNoExistente = 999L; // Suponiendo que este ID no existe en la base de datos
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(idNoExistente));
    }

    @Test
    @Order(6)
    void deberiaModificarPacienteExistente() {
        DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Calle", 123, "Sol", "Pool");

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Carlos", "Lopez", 12345, domicilioEntradaDto);

        Long idPaciente = 1L; // Suponiendo que este ID existe en la base de datos

        // Verifica que se lance la excepción ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.modificarPaciente(pacienteEntradaDto, idPaciente));
    }
}
