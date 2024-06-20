package com.backend.clinica_odontologica.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "fecha_y_hora")
    private LocalDateTime fechaYHora;

    @OneToOne
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Turno() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Turno(Long id, LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Turno: " +
                "id: " + id +
                ", fechaYHora: " + fechaYHora +
                ", odontologo: " + odontologo +
                ", paciente: " + paciente;
    }
}
