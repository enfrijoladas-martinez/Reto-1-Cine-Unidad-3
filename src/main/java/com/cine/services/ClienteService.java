package com.cine.services;

import com.cine.models.Cliente;
import com.cine.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente registrar(Cliente cliente) {
        cliente.setRegistrado(true);
        return clienteRepository.save(cliente);
    }

    // TODO: consultar puntos acumulados de un cliente
}
