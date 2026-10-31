package com.cine.repositories;

import com.cine.models.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoletoRepository extends JpaRepository<Boleto, Long> {

    List<Boleto> findByFuncionId(Long funcionId);

    List<Boleto> findByFuncionIdentifier(Long funcionId);

    List<Boleto> findByClienteMail(String email);

    // TODO: verificar si un asiento (fila, numero) ya está ocupado en una función
}
