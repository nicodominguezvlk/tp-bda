package com.abnimavade.tp.controllers;


import com.abnimavade.tp.dtos.AlquilerDTO;
import com.abnimavade.tp.services.AlquilerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquiler")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService){
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public ResponseEntity<List<AlquilerDTO>> getAll(){
        List<AlquilerDTO> values = this.alquilerService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<AlquilerDTO> add(@RequestBody AlquilerDTO alquilerDto){
        AlquilerDTO a = this.alquilerService.add(alquilerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AlquilerDTO> delete(@PathVariable Long id) {
        AlquilerDTO response = alquilerService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AlquilerDTO> update(@PathVariable Long id, @RequestBody AlquilerDTO alquilerDTO) {
        AlquilerDTO updatedAlquiler = alquilerService.update(alquilerDTO);
        if (updatedAlquiler != null) {
            return ResponseEntity.ok(updatedAlquiler);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

