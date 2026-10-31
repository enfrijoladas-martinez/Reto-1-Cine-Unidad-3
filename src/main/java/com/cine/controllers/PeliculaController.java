package com.cine.controllers;

import com.cine.models.Clasificacion;
import com.cine.models.Pelicula;
import com.cine.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> listar(@RequestParam(required = false) Clasificacion clasificacion) {
        if (clasificacion != null) {
            return peliculaService.porClasificacion(clasificacion);
        }
        return peliculaService.listar();
    }

    @GetMapping("/{id}")
    public Pelicula obtener(@PathVariable Long id) {
        return peliculaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        return peliculaService.crear(pelicula);
    }
}
