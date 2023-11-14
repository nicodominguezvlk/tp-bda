package com.abnimavade.estaciones.repos;

import com.abnimavade.estaciones.models.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
