package com.cine.controllers;

import com.cine.models.Boleto;
import com.cine.services.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

    @GetMapping("/funcion/{funcionId}")
    public List<Boleto> porFuncion(@PathVariable Long funcionId) {
        return boletoService.porFuncion(funcionId);
    }

    @GetMapping
    public Boleto comprar(@RequestParam Long funcionId,
                          @RequestParam Long clienteId,
                          @RequestParam String fila,
                          @RequestParam int numero) {
        return boletoService.comprar(funcionId, clienteId, fila, numero);
    }
}
