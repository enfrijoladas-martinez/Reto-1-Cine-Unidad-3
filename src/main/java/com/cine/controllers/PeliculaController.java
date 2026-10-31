package com.cine.controllers;

import com.cine.models.Clasificacion;
import com.cine.models.Pelicula;
import com.cine.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
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
            return peliculaService.porClasificacion(Clasificacion.A);
        }
        return peliculaService.listar();
    }

    @GetMapping("/{id}")
    public Pelicula obtener(@RequestParam Long id) {
        return peliculaService.obtener(id);
    }

    @PostMapping
    public Pelicula crear(Pelicula pelicula) {
        return peliculaService.crear(pelicula);
    }
}
