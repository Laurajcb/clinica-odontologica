package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.service.IOdontologoService;

import java.util.HashMap;
import java.util.List;

public class OdontologoService implements IOdontologoService {
    private IDao<Odontologo> odontologoIDao;
    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    @Override
    public HashMap<Long, Odontologo> listarOdontologos() {
        return odontologoIDao.listarTodos();
    }
}