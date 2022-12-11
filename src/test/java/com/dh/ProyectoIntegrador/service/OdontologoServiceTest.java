package com.dh.ProyectoIntegrador.service;

import com.dh.ProyectoIntegrador.entity.Odontologo;
import com.dh.ProyectoIntegrador.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologoTest(){
        Odontologo odontologoAGuardar= new Odontologo("A33","Salem","Sinual");
        Odontologo odontologoGuardado=odontologoService.guardarOdontologo(odontologoAGuardar);
        assertEquals(1L,odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest(){
        Long idABuscar=1L;
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(idABuscar);
        assertNotNull(odontologoBuscado.get());
    }
    @Test
    @Order(3)
    public void buscarOdontologosTest(){
        List<Odontologo> odontologos= odontologoService.listarOdontologos();
        Integer cantidadEsperada=1;
        assertEquals(cantidadEsperada,odontologos.size());
    }
    @Test
    @Order(4)
    public void actualizarOdontologoTest(){
        Odontologo odontologoAActualizar = new Odontologo("A22","Matias","Sinual");
        odontologoService.actualizarOdontologo(odontologoAActualizar);
        Optional<Odontologo> odontologoActualizado= odontologoService.buscarOdontologoXId(odontologoAActualizar.getId());
        assertEquals("Matias",odontologoActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        Long idAEliminar=1L;
        odontologoService.eliminarOdontologo(idAEliminar);
        Optional<Odontologo> odontologoEliminado=odontologoService.buscarOdontologoXId(idAEliminar);
        assertFalse(odontologoEliminado.isPresent());
    }

}