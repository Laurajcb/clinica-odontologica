package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
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
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarseUnOdontologoDeNombreJuanYRetornarSuId() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("RM-456789", "Juan", "Martinez");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Juan", odontologoSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeOdontologos() {
        HashMap<Long, OdontologoSalidaDto> listadoDeOdontologos = odontologoService.listarOdontologos();

        assertFalse(listadoDeOdontologos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElOdontologoConId1() {
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDeOdontologos() {
        HashMap<Long, OdontologoSalidaDto> listadoDeOdontologos = odontologoService.listarOdontologos();

        assertTrue(listadoDeOdontologos.isEmpty());
    }

    @Test
    @Order(5)
    void deberiaLanzarResourceNotFoundExceptionAlEliminarOdontologoInexistente() {
        Long idNoExistente = 999L;

        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(idNoExistente));
    }

    @Test
    @Order(6)
    void deberiaLanzarResourceNotFoundExceptionAlModificarOdontologoInexistente() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("RM-456789", "Mario", "Gomez");
        Long idOdontologo = 1L;

        assertThrows(ResourceNotFoundException.class, () -> odontologoService.modificarOdontologo(odontologoEntradaDto, idOdontologo));
    }
}
