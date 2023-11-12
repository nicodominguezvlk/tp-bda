package com.abnimavade.tp.controllers;


import com.abnimavade.tp.dtos.TarifaDTO;
import com.abnimavade.tp.services.TarifaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarifa")
public class TarifaController {

    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService)
    {
        this.tarifaService = tarifaService;
    }

    @GetMapping
    public ResponseEntity<List<TarifaDTO>> getAll(){
        List<TarifaDTO> values = this.tarifaService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<TarifaDTO> add(@RequestBody TarifaDTO tarifaDto){
        TarifaDTO t = this.tarifaService.add(tarifaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TarifaDTO> delete(@PathVariable Long id) {
        TarifaDTO response = tarifaService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TarifaDTO> update(@PathVariable Long id, @RequestBody TarifaDTO tarifaDTO) {
        TarifaDTO updatedTarifa = tarifaService.update(tarifaDTO);
        if (updatedTarifa != null) {
            return ResponseEntity.ok(updatedTarifa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
