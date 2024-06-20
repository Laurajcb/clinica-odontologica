package com.backend.clinica_odontologica.dto.salida;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;

    private LocalDateTime fechaYHora;

    private OdontologoNombreApellidoSalidaDto odontologo;

    private PacienteNombreApellidoSalidaDto paciente;

    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(Long id, LocalDateTime fechaYHora, OdontologoNombreApellidoSalidaDto odontologo, PacienteNombreApellidoSalidaDto paciente) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
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

    public OdontologoNombreApellidoSalidaDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoNombreApellidoSalidaDto odontologo) {
        this.odontologo = odontologo;
    }

    public PacienteNombreApellidoSalidaDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteNombreApellidoSalidaDto paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", FECHA Y HORA: " + fechaYHora +
                ", ODONTOLOGO: " + odontologo +
                ", PACIENTE: " + paciente;
    }
}
