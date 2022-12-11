package com.dh.ProyectoIntegrador;

import com.dh.ProyectoIntegrador.dto.TurnoDTO;
import com.dh.ProyectoIntegrador.entity.Domicilio;
import com.dh.ProyectoIntegrador.entity.Odontologo;
import com.dh.ProyectoIntegrador.entity.Paciente;
import com.dh.ProyectoIntegrador.service.OdontologoService;
import com.dh.ProyectoIntegrador.service.PacienteService;
import com.dh.ProyectoIntegrador.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    private void cargarTurnoInicial(){
        Domicilio domicilio= new Domicilio("Los Eslavos",8516,"Comodoro Rivadavia","Chubut");
        Paciente paciente= new Paciente("Juan","Tebes","8878",
                LocalDate.of(2022,12,7),"juan.tebes@gmail.com",domicilio);
        Paciente pacienteGuardado=pacienteService.guardarPaciente(paciente);
        Odontologo odontologo=new Odontologo("JT8556","Franco","Falasconi");
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologo);
        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setFecha(LocalDate.of(2022,12,7));
        turnoDTO.setPaciente_id(pacienteGuardado.getId());
        turnoDTO.setOdontologo_id(odontologoGuardado.getId());
        turnoService.guardarTurno(turnoDTO);
    }
    @Test
    public void listadoTurnoTest() throws Exception {
        cargarTurnoInicial();
        MvcResult respuesta=mockMvc.perform(MockMvcRequestBuilders
                        .get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
