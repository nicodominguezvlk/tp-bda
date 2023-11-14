package com.abnimavade.alquileres.controllers;


import com.abnimavade.alquileres.dtos.AlquilerDTO;
import com.abnimavade.alquileres.dtos.AlquilerInicioDTO;
import com.abnimavade.alquileres.models.Alquiler;
import com.abnimavade.alquileres.services.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alquileres")
public class AlquilerController {


    private final AlquilerService alquilerService;
    @Autowired

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

   /* @PostMapping("/create")
    public ResponseEntity<AlquilerDTO> createAlquiler(@RequestBody AlquilerDTO alquilerDTO) {
        AlquilerDTO createdAlquiler = alquilerService.create(alquilerDTO);
        return ResponseEntity.ok(createdAlquiler);
    }*/

    @DeleteMapping("/delete/{Alquilerid}")
    // VALE: Tiene que devolver String porque cuando se elimina un objeto, no se puede devolver el objeto eliminado, devolvemos un mensaje de "Alquiler deleted".
    public ResponseEntity<String> delete(@PathVariable Long Alquilerid) {
        String response = alquilerService.delete(Alquilerid);
        return ResponseEntity.ok(response);
    }

   /* @PutMapping("/update/{id}")
    public ResponseEntity<AlquilerDTO> update(@PathVariable Long id, @RequestBody AlquilerDTO alquilerDTO) {
        if (!alquilerService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        else{
            AlquilerDTO updatedAlquiler = alquilerService.update(id,alquilerDTO);
            return ResponseEntity.ok(updatedAlquiler);
        }

    }*/

    // Crear un alquiler de bicileta desde una estacion dada
    @PostMapping("/createDesde")
    public ResponseEntity<Alquiler> createAlquilerDesde(@RequestBody AlquilerInicioDTO alquilerInicioDTO) {
        Alquiler createdAlquiler = alquilerService.createDesde(alquilerInicioDTO);
        return ResponseEntity.ok(createdAlquiler);
    }

    // Mostrar los alquileres cuyas tarifas sean mayores a cierto monto
    @GetMapping("/tarifasMayores/{monto}")
    public ResponseEntity<List<AlquilerDTO>> getAlquileresTarifasMayores(@PathVariable double monto) {
        List<AlquilerDTO> alquileresTarifasMayores = alquilerService.getAlquileresTarifasMayores(monto);
        return ResponseEntity.ok(alquileresTarifasMayores);
    }

    // Finalizar un alquiler en curso, informando los datos del mismo.
    @PutMapping("/finalizar/{id}/{moneda}/{estacionDevolucionId}/{fechaHoraDevolucion}")
    public ResponseEntity<AlquilerDTO> finalizarAlquiler(@PathVariable Long id, @RequestBody AlquilerDTO alquilerDTO, @PathVariable String moneda,
                                                         @PathVariable int estacionId,@PathVariable LocalDateTime fechaHoraDevolucion)
    {
        // Buscamos si existe el alquiler con el id dado
        if (!alquilerService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        else{
            // Si existe, lo finalizamos
            AlquilerDTO updatedAlquiler = alquilerService.finalizarAlquiler(id, moneda, alquilerDTO, estacionId, fechaHoraDevolucion);
            return ResponseEntity.ok(updatedAlquiler);
        }
    }
}

