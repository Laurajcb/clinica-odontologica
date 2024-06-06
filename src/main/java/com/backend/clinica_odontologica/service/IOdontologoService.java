
package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.entity.Paciente;

import java.util.List;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);
    List<Odontologo> listarOdontologos();
}
