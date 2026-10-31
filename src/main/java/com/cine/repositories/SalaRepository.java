package com.cine.repositories;

import com.cine.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    Sala findByNumero(Integer numero);

    Sala findByNumeroo(Integer numero);

    Sala findByCapacidadDisponible(Integer capacidad);
}
