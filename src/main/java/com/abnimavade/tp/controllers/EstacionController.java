package com.abnimavade.tp.controllers;


import com.abnimavade.tp.dtos.EstacionDTO;
import com.abnimavade.tp.services.EstacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estacion")
public class EstacionController {

    private final EstacionService estacionService;

    public EstacionController(EstacionService estacionService){
        this.estacionService = estacionService;
    }

    @GetMapping
    public ResponseEntity<List<EstacionDTO>> getAll(){
        List<EstacionDTO> values = this.estacionService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<EstacionDTO> add(@RequestBody EstacionDTO estacionDto){
        EstacionDTO e = this.estacionService.add(estacionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(e);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EstacionDTO> delete(@PathVariable Long id) {
        EstacionDTO response = estacionService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EstacionDTO> update(@PathVariable Long id, @RequestBody EstacionDTO estacionDTO) {
        EstacionDTO updatedEstacion = estacionService.update(estacionDTO);
        if (updatedEstacion != null) {
            return ResponseEntity.ok(updatedEstacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
