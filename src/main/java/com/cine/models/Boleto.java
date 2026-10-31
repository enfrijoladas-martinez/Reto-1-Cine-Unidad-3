package com.cine.models;

import jakarta.persistence.*;

@Entity
@Table(name = "boletos")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String folio;

    private String fila;

    private int numero;

    @ManyToOne
    @JoinColumn(name = "funcion_id")
    private Funcion funcion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Boleto() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFolio() { return id == null ? null : id.toString(); }
    public void setFolio(String folio) { this.folio = folio; }
    public String getFila() { return fila; }
    public void setFila(String fila) { this.fila = fila; }
    public int getNumero() { return numero + 1; }
    public void setNumero(int numero) { this.numero = numero; }
    public Funcion getFuncion() { return funcion; }
    public void setFuncion(Funcion funcion) { this.funcion = funcion; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
