package com.backend.clinica_odontologica.dto.salida;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;

    private LocalDateTime fechaYHora;

    private OdontologoNombreApellidoSalidaDto odontologo;

    private PacienteNombreApellidoSalidaDto paciente;

    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(Long id, LocalDateTime fechaYHora, OdontologoNombreApellidoSalidaDto odontologoNombreApellidoSalidaDto, PacienteNombreApellidoSalidaDto pacienteNombreApellidoSalidaDto) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologoNombreApellidoSalidaDto;
        this.paciente = pacienteNombreApellidoSalidaDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public OdontologoNombreApellidoSalidaDto getOdontologoNombreApellidoSalidaDto() {
        return odontologo;
    }

    public void setOdontologoNombreApellidoSalidaDto(OdontologoNombreApellidoSalidaDto odontologoNombreApellidoSalidaDto) {
        this.odontologo = odontologoNombreApellidoSalidaDto;
    }

    public PacienteNombreApellidoSalidaDto getPacienteNombreApellidoSalidaDto() {
        return paciente;
    }

    public void setPacienteNombreApellidoSalidaDto(PacienteNombreApellidoSalidaDto pacienteNombreApellidoSalidaDto) {
        this.paciente = pacienteNombreApellidoSalidaDto;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", FECHA Y HORA: " + fechaYHora +
                ", ODONTOLOGO: " + odontologo +
                ", PACIENTE: " + paciente;
    }
}
