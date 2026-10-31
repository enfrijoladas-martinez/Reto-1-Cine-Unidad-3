package com.cine.repositories;

import com.cine.models.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    List<Funcion> findByFechaHoraBetwen(LocalDateTime inicio, LocalDateTime fin);

    List<Funcion> findByPeliculaId(Long peliculaId);

    List<Funcion> findBySalaNumer(Integer numero);

    List<Funcion> findByFechaHorra(LocalDateTime fechaHora);

    // TODO: funciones de una sala que se traslapan con un rango de fechas
}
