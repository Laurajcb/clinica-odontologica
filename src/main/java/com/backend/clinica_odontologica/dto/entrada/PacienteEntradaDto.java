package com.backend.clinica_odontologica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class PacienteEntradaDto {
    @NotBlank(message = "El campo nombre no puede estar en blanco")
    @Size(max = 50, message = "El nombre debe tener maximo 50 caracteres")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar en blanco")
    @Size(max = 50, message = "El nombre debe tener maximo 50 caracteres")
    private String apellido;

    @Positive(message = "El campo dni no puede ser nulo o menor a cero")
    @Digits(integer = 8, fraction = 0, message = "El dni debe tener como maximo 8 digitos")
    private int dni;

    @FutureOrPresent(message = "La fecha de ingreso no puede ser anterior al dia de hoy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @NotNull(message = "El campo domicilio del paciente no puede ser nulo")
    @Valid
    private DomicilioEntradaDto domicilio;

    public PacienteEntradaDto() {
    }

    public PacienteEntradaDto(String nombre, String apellido, int dni, DomicilioEntradaDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
         this.fechaIngreso = LocalDate.now();
        this.domicilio = domicilio;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioEntradaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntradaDto domicilio) {
        this.domicilio = domicilio;
    }
}
