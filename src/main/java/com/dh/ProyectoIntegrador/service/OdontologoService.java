package com.dh.ProyectoIntegrador.service;

import com.dh.ProyectoIntegrador.entity.Odontologo;
import com.dh.ProyectoIntegrador.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private static final Logger LOGGER=Logger.getLogger(OdontologoService.class);

    private OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        LOGGER.info("Se comenzó el guardado de un odontologo con apellido: " + odontologo.getApellido());
        return odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
        LOGGER.warn("Se eliminó el odontologo con id: " + id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        LOGGER.info("Se comenzó la actualización del odontologo: " + odontologo.getId());
        odontologoRepository.save(odontologo);
    }

    public List<Odontologo> listarOdontologos(){
        LOGGER.info("Se comenzó el listado de odontologos");
        return odontologoRepository.findAll();
    }


    public Optional<Odontologo> buscarOdontologoXId(Long id){
        LOGGER.info("Se comenzó la busqueda del odontologo: " + id);
        return odontologoRepository.findById(id);
    }


}
