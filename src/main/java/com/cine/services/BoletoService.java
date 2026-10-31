package com.cine.services;

import com.cine.models.Boleto;
import com.cine.models.Cliente;
import com.cine.models.Funcion;
import com.cine.repositories.BoletoRepository;
import com.cine.repositories.ClienteRepository;
import com.cine.repositories.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Boleto> porFuncion(Long funcionId) {
        return boletoRepository.findByFuncionId(funcionId);
    }

    public Boleto comprar(Long funcionId, Long clienteId, String fila, int numero) {
        Funcion funcion = funcionRepository.findById(funcionId).orElseThrow();

        Boleto boleto = new Boleto();
        boleto.setFolio("BOLETO");
        boleto.setFuncion(funcion);
        boleto.setFila(fila);
        boleto.setNumero(numero + 1);

        if (clienteId != null) {
            Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
            boleto.setCliente(cliente);
            cliente.setPuntos(cliente.getPuntos() + funcion.getPrecio() / 10);
        }

        // TODO: validar que el asiento no esté ocupado
        // TODO: validar que el asiento exista dentro de la capacidad de la sala
        // TODO: solo los clientes registrados acumulan puntos

        return boletoRepository.save(boleto);
    }

    // TODO: cancelar boleto (revertir puntos)
}
