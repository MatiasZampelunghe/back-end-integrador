package com.dh.ProyectoIntegrador.service;

import com.dh.ProyectoIntegrador.dto.TurnoDTO;
import com.dh.ProyectoIntegrador.entity.Odontologo;
import com.dh.ProyectoIntegrador.entity.Paciente;
import com.dh.ProyectoIntegrador.entity.Turno;
import com.dh.ProyectoIntegrador.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger LOGGER=Logger.getLogger(PacienteService.class);
    private TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public TurnoDTO guardarTurno (TurnoDTO turno){
        LOGGER.info("Se comenzó el guardado del turno con id: " + turno.getId());
        Turno turnoAGuardar=turnoDTOaTurno(turno);
        Turno turnoGuardado=turnoRepository.save(turnoAGuardar);
        return turnoATurnoDTO(turnoGuardado);
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    public void actualizarTurno(TurnoDTO turno){
        Turno turnoAActualizar=turnoDTOaTurno(turno);
        turnoRepository.save(turnoAActualizar);
    }
    public Optional<TurnoDTO> buscarTurno(Long id){
        LOGGER.info("se comenzó la busqueda del turno con id: " + id);
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else{
            return Optional.empty();
        }
    }
    public List<TurnoDTO> buscarTodosTurno(){
        LOGGER.info("Se comenzo el listado de todos los turnos");
        List<Turno>turnosEncontrados=turnoRepository.findAll();
        List<TurnoDTO> respuesta= new ArrayList<>();
        for (Turno t:turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(t));
        }
        return respuesta;
    }
    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO respuesta=new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFecha());
        respuesta.setOdontologo_id(turno.getOdontologo().getId());
        respuesta.setPaciente_id(turno.getPaciente().getId());
        return respuesta;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        paciente.setId(turnoDTO.getPaciente_id());
        odontologo.setId(turnoDTO.getOdontologo_id());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        return turno;
    }


}
