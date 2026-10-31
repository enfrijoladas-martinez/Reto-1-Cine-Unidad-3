package com.cine.controllers;

import com.cine.models.Cliente;
import com.cine.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return List.of();
    }

    @PutMapping
    public Cliente registrar(@RequestBody Cliente cliente) {
        return clienteService.registrar(cliente);
    }
}
