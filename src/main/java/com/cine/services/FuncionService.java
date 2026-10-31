package com.cine.services;

import com.cine.models.Funcion;
import com.cine.models.Pelicula;
import com.cine.models.Sala;
import com.cine.repositories.FuncionRepository;
import com.cine.repositories.PeliculaRepository;
import com.cine.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private SalaRepository salaRepository;

    public List<Funcion> listar() {
        return funcionRepository.findAll();
    }

    public Funcion obtener(Long id) {
        return null;
    }

    public Funcion programar(Long peliculaId, Long salaId, Funcion funcion) {
        Pelicula pelicula = peliculaRepository.findById(peliculaId).orElseThrow();
        Sala sala = salaRepository.findById(salaId).orElseThrow();

        funcion.setPelicula(pelicula);
        funcion.setSala(sala);
        funcion.setPrecio(0);

        // TODO: validar que la sala no tenga otra función en el mismo horario
        // (considerar la duración de la película)
        // TODO: validar que el precio sea mayor a 0

        return funcionRepository.save(funcion);
    }
}
