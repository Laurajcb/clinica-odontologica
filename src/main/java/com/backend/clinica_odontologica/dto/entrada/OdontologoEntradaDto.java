package com.backend.clinica_odontologica.dto.entrada;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {
    @NotBlank(message = "El campo matricula no puede estar en blanco")
    @Size(max = 50, message = "La matricula debe tener maximo 50 caracteres")
    private String matricula;

    @NotBlank(message = "El campo nombre no puede estar en blanco")
    @Size(max = 50, message = "El nombre debe tener maximo 50 caracteres")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar en blanco")
    @Size(max = 50, message = "El apellido debe tener maximo 50 caracteres")
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
