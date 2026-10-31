package com.cine.services;

import com.cine.models.Clasificacion;
import com.cine.models.Pelicula;
import com.cine.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> listar() {
        return peliculaRepository.findAll();
    }

    public Pelicula obtener(Long id) {
        return peliculaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la película " + id));
    }

    public List<Pelicula> porClasificacion(Clasificacion clasificacion) {
        return peliculaRepository.findByClasificacion(clasificacion);
    }

    public Pelicula crear(Pelicula pelicula) {
        // TODO: validar título obligatorio y duración mayor a 0
        return peliculaRepository.save(pelicula);
    }

    // TODO: actualizar y eliminar (no se puede eliminar si tiene funciones)
}
