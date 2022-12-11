package com.dh.ProyectoIntegrador.service;

import com.dh.ProyectoIntegrador.dto.TurnoDTO;
import com.dh.ProyectoIntegrador.entity.Domicilio;
import com.dh.ProyectoIntegrador.entity.Odontologo;
import com.dh.ProyectoIntegrador.entity.Paciente;
import com.dh.ProyectoIntegrador.entity.Turno;
import com.dh.ProyectoIntegrador.exception.BadRequestException;
import com.dh.ProyectoIntegrador.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarTurnoTest(){
        Odontologo odontologoAGuardar= new Odontologo("A33","Salem","Sinual");
        Paciente pacienteAGuardar= new Paciente("Matias","Zampelunghe"
                ,"8324", LocalDate.of(2022,12,7),"matizampe11@gmail.com",
                new Domicilio("80512",593,"La Plata","Buenos aires"));
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologoAGuardar);
        Paciente pacienteGuardado= pacienteService.guardarPaciente(pacienteAGuardar);
        TurnoDTO turnoAGuardar = new TurnoDTO(1L,(LocalDate.of(2022,8,1)), pacienteGuardado.getId(), odontologoGuardado.getId());
        TurnoDTO turnoGuardado=turnoService.guardarTurno(turnoAGuardar);
       assertEquals(1L,turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPorId() throws ResourceNotFoundException {
        Long idABuscar=1L;
        Optional<TurnoDTO> pacienteBuscado=turnoService.buscarTurno(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }
    @Test
    @Order(3)
    public void ListarTodos() throws ResourceNotFoundException {
        List<TurnoDTO> listTurno= turnoService.buscarTodosTurno();
        Integer listSize=1;
        assertEquals(listSize,listTurno.size());
    }
    @Test
    @Order(4)
    public void ActualizarTurno() throws BadRequestException, ResourceNotFoundException {
        Odontologo odontologoAGuardar = new Odontologo("2323","Tom","R");
        Paciente pacienteAGuardar = new Paciente("Tomas","Rodriguez","232323", LocalDate.of(2003,5,5),
                "tom@gmail.com", new Domicilio("Chubut",123,"Bsas","Bsas"));
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        LocalDate fechaAModificar = LocalDate.of(2022,11,2);
        TurnoDTO turnoAModificar= new TurnoDTO(1L,fechaAModificar,odontologoGuardado.getId(),pacienteGuardado.getId());
        turnoService.actualizarTurno(turnoAModificar);
        assertEquals(fechaAModificar,turnoAModificar.getFecha());
    }
    @Test
    @Order(5)
    public void EliminarTurno() throws ResourceNotFoundException, BadRequestException {
        Long idABuscar=1L;
        turnoService.eliminarTurno(idABuscar);
        Optional<TurnoDTO> turnoEliminado=turnoService.buscarTurno(idABuscar);
        assertFalse(turnoEliminado.isPresent());
    }

}