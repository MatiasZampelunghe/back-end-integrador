package com.dh.ProyectoIntegrador.dto;

import java.time.LocalDate;

public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    private Long paciente_id;
    private Long odontologo_id;

    public TurnoDTO(Long id, LocalDate fecha, Long paciente_id, Long odontologo_id) {
        this.id = id;
        this.fecha = fecha;
        this.paciente_id = paciente_id;
        this.odontologo_id = odontologo_id;
    }

    public TurnoDTO() {
    }

    public TurnoDTO(LocalDate fecha, Long paciente_id, Long odontologo_id) {
        this.fecha = fecha;
        this.paciente_id = paciente_id;
        this.odontologo_id = odontologo_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public Long getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Long odontologo_id) {
        this.odontologo_id = odontologo_id;
    }
}
