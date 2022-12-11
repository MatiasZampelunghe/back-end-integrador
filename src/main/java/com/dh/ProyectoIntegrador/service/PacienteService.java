package com.dh.ProyectoIntegrador.service;
import com.dh.ProyectoIntegrador.entity.Paciente;
import com.dh.ProyectoIntegrador.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger LOGGER=Logger.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {

        this.pacienteRepository = pacienteRepository;
    }
    public Paciente guardarPaciente (Paciente paciente){
        LOGGER.info("Se comenzó el guardado de un paciente con apellido: " + paciente.getApellido());
        return pacienteRepository.save(paciente);
    }
    public void eliminarPaciente(Long id){

        pacienteRepository.deleteById(id);
        LOGGER.warn("se borro al paciente con id: " + id);
    }
    public void actualizarPaciente(Paciente paciente){
        LOGGER.info("Se comenzó la actualizacion de un paciente con apellido: " + paciente.getApellido());
        pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id){
        LOGGER.info("Se comenzó la busqueda de un paciente con id: " + id);
        return pacienteRepository.findById(id);
    }
    public List<Paciente> buscarTodosPacientes(){
        LOGGER.info("Se comenzó el listado de todos los pacientes ");
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPacienteByEmail(String email){
        LOGGER.info("Se comenzó la busqueda del paciente con id: " + email);
        return pacienteRepository.findByEmail(email);
    }
}
