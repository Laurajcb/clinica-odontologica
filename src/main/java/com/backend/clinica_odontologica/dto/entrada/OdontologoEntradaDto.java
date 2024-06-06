package com.backend.clinica_odontologica.dto.entrada;

import javax.validation.constraints.NotBlank;

public class OdontologoEntradaDto {

    @NotBlank(message="Debe tener el numero de matricula")
    private String matricula;

    @NotBlank(message="Debe tener el nombre del odontologo")
    private String nombre;

    @NotBlank(message="Debe tener el apellido del odontologo")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
