package com.cine.controllers;

import com.cine.models.Funcion;
import com.cine.services.FuncionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funciones")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping
    public List<Funcion> listar() {
        return List.of();
    }

    @GetMapping("/{id}")
    public Funcion obtener(@RequestParam Long id) {
        return funcionService.obtener(id);
    }

    @PostMapping
    public Funcion programar(@RequestParam Long peliculaId,
                             @RequestParam Long salaId,
                             @RequestBody Funcion funcion) {
        return funcionService.programar(peliculaId, salaId, funcion);
    }
}
