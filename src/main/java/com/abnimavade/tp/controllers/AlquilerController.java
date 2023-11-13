package com.abnimavade.tp.controllers;


import com.abnimavade.tp.dtos.AlquilerDTO;
import com.abnimavade.tp.services.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/alquileres")
public class AlquilerController {

    @Autowired
    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService){
        this.alquilerService = alquilerService;
    }

    // VALE: HAY QUE HACER UN GET PARA CADA ENTIDAD
    // Y PONER EL NOMBRE DE LA ENTIDAD EN EL PATH
    @GetMapping("/all")
    public ResponseEntity<List<AlquilerDTO>> getAll(){
        List<AlquilerDTO> values = this.alquilerService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping("/create")
    public ResponseEntity<AlquilerDTO> createAlquiler(@RequestBody AlquilerDTO alquilerDTO) {
        AlquilerDTO createdAlquiler = alquilerService.create(alquilerDTO);
        return ResponseEntity.ok(createdAlquiler);
    }
    @DeleteMapping("/delete/{Alquilerid}")
    // VALE: Tiene que devolver String porque cuando se elimina un objeto, no se puede devolver el objeto eliminado, devolvemos un mensaje de "Alquiler deleted".
    public ResponseEntity<String> delete(@PathVariable Long Alquilerid) {
        String response = alquilerService.delete(Alquilerid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AlquilerDTO> update(@PathVariable Long id, @RequestBody AlquilerDTO alquilerDTO) {
        AlquilerDTO updatedAlquiler = alquilerService.update(id, alquilerDTO);
        if (updatedAlquiler != null) {
            return ResponseEntity.ok(updatedAlquiler);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

