package com.abnimavade.tp.repositories;

import com.abnimavade.tp.models.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
