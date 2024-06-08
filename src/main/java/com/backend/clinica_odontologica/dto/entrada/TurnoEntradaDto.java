package com.backend.clinica_odontologica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @FutureOrPresent(message = "La fecha y hora del turno no pueden ser anterior a la actual")
    @NotNull(message = "El campo fecha y hora de turno no puede ser nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El campo id de odontologo del turno no puede ser nulo")
    @Valid
    private OdontologoEntradaDto odontologo;

    @NotNull(message = "El campo id de paciente del turno no puede ser nulo")
    @Valid
    private PacienteEntradaDto paciente;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(LocalDateTime fechaYHora, OdontologoEntradaDto odontologoEntradaDto, PacienteEntradaDto pacienteEntradaDto) {
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologoEntradaDto;
        this.paciente = pacienteEntradaDto;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public OdontologoEntradaDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        this.odontologo = odontologoEntradaDto;
    }

    public PacienteEntradaDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntradaDto pacienteEntradaDto) {
        this.paciente = pacienteEntradaDto;
    }
}
