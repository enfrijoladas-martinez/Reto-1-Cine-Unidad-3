package com.cine.services;

import com.cine.models.Boleto;
import com.cine.models.Cliente;
import com.cine.models.Funcion;
import com.cine.repositories.BoletoRepository;
import com.cine.repositories.ClienteRepository;
import com.cine.repositories.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public Boleto comprar(Long funcionId, Long clienteId, String fila, int numero) {
        Funcion funcion = funcionRepository.findById(funcionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la función " + funcionId));

        Boleto boleto = new Boleto();
        boleto.setFolio(UUID.randomUUID().toString());
        boleto.setFuncion(funcion);
        boleto.setFila(fila);
        boleto.setNumero(numero);

        if (clienteId != null) {
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el cliente " + clienteId));
            boleto.setCliente(cliente);
            cliente.setPuntos(cliente.getPuntos() + (int) (funcion.getPrecio() / 10));
        }

        // TODO: validar que el asiento no esté ocupado
        // TODO: validar que el asiento exista dentro de la capacidad de la sala
        // TODO: solo los clientes registrados acumulan puntos

        return boletoRepository.save(boleto);
    }

    // TODO: cancelar boleto (revertir puntos)
}
