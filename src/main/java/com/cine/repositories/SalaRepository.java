package com.cine.repositories;

import com.cine.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    Sala findByNumero(Integer numero);

    List<Sala> findByCapacidadGreaterThanEqual(Integer capacidad);
}
