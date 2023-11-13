package com.abnimavade.tp.controllers;


import com.abnimavade.tp.dtos.EstacionDTO;
import com.abnimavade.tp.services.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/estaciones")
public class EstacionController {

    @Autowired
    private final EstacionService estacionService;

    public EstacionController(EstacionService estacionService){
        this.estacionService = estacionService;
    }
    // VALE: HAY QUE HACER UN GET PARA CADA ENTIDAD
    // Y PONER EL NOMBRE DE LA ENTIDAD EN EL PATH
    @GetMapping("/allEstaciones")
    public ResponseEntity<List<EstacionDTO>> getAll(){
        List<EstacionDTO> values = this.estacionService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping("/create")
    public ResponseEntity<EstacionDTO> createEstacion(@RequestBody EstacionDTO estacionDTO) {
        EstacionDTO createdEstacion = estacionService.create(estacionDTO);
        return ResponseEntity.ok(createdEstacion);
    }

    // VALE: Debe devolver un String, no puede devolver el objeto eliminado. Devulve un "Estacion deleted"
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String response = estacionService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EstacionDTO> update(@PathVariable Long id, @RequestBody EstacionDTO estacionDTO) {
        EstacionDTO updatedEstacion = estacionService.update(id,estacionDTO);
        if (updatedEstacion != null) {
            return ResponseEntity.ok(updatedEstacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
