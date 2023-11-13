package com.abnimavade.tp.controllers;


import com.abnimavade.tp.dtos.TarifaDTO;
import com.abnimavade.tp.services.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/tarifas")
public class TarifaController {

    @Autowired
    // El final es para que no se pueda cambiar el valor de la variable, yo creo que no hace falta
    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService)
    {
        this.tarifaService = tarifaService;
    }

    // VALE: HAY QUE HACER UN GET PARA CADA ENTIDAD
    // Y PONER EL NOMBRE DE LA ENTIDAD EN EL PATH
    @GetMapping("/allTarifas")
    public ResponseEntity<List<TarifaDTO>> getAll(){
        List<TarifaDTO> values = this.tarifaService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping("/create")
    public ResponseEntity<TarifaDTO> createTarifa(@RequestBody TarifaDTO tarifaDTO) {
        TarifaDTO createdTarifa = tarifaService.create(tarifaDTO);
        return ResponseEntity.ok(createdTarifa);
    }
    // VALE: Debe devolver un String, no puede devolver el objeto eliminado. Devulve un "Tarifa deleted"
    @DeleteMapping("/delete/{idTarifa}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String response = tarifaService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TarifaDTO> update(@PathVariable Long id, @RequestBody TarifaDTO tarifaDTO) {
        // Podemos mandar el id de aca directamente
        TarifaDTO updatedTarifa = tarifaService.update(id,tarifaDTO);
        if (updatedTarifa != null) {
            return ResponseEntity.ok(updatedTarifa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
