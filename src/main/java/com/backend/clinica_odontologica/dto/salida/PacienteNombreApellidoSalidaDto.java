package com.backend.clinica_odontologica.dto.salida;

public class PacienteNombreApellidoSalidaDto {
    private Long id;

    private String nombre;

    private String apellido;

    private int dni;

    public PacienteNombreApellidoSalidaDto() {
    }

    public PacienteNombreApellidoSalidaDto(Long id, String nombre, String apellido, int dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "id: " + id +
                ", nombreCompleto: " + nombre + " " + apellido;
    }
}
