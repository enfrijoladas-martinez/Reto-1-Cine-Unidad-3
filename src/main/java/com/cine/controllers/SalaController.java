package com.cine.controllers;

import com.cine.models.Sala;
import com.cine.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public List<Sala> listar() {
        return List.of();
    }

    @PutMapping
    public Sala crear(@RequestBody Sala sala) {
        return salaRepository.save(sala);
    }
}
