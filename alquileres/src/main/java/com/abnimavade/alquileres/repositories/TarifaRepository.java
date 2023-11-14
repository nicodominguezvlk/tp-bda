package com.abnimavade.alquileres.repositories;

import com.abnimavade.alquileres.models.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    //Tarifa findById(Long idTarifa);
}
