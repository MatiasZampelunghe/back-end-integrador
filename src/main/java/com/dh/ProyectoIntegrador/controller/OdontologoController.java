package com.dh.ProyectoIntegrador.controller;

import com.dh.ProyectoIntegrador.entity.Odontologo;
import com.dh.ProyectoIntegrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//rest o no rest
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {

        this.odontologoService = odontologoService;
    }
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino al odontologo " +
                    "con id= "+id);
        }
        else{
            return ResponseEntity.badRequest().body("No se encuentra un odontologo con id= "
                    +id+" . Verificar el ingreso.");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizó el odontologo con " +
                    "apellido "+odontologo.getApellido());
        }
        else{
            return ResponseEntity.badRequest().body("El odontologo con id= "+
                    odontologo.getId()+" no existe en la BD." +
                    "No puede actualizar algo que no existe :(");
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping
//    public String buscarOdontologoPorID(Model model, @RequestParam("id") Integer id){
//        Odontologo odontologoBuscado=odontologoService.buscarOdontologoXId(id);
//        model.addAttribute("nombre",odontologoBuscado.getNombre());
//        model.addAttribute("apellido",odontologoBuscado.getApellido());
//        model.addAttribute("matricula",odontologoBuscado.getMatricula());
//        //como return, mandas el nombre de la vista
//        return "busOdontologo";
//    }

}
