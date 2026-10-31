package com.cine.models;

import jakarta.persistence.*;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private Integer capacidad;

    public Sala() {
    }

    public Sala(Integer numero, Integer capacidad) {
        this.numero = capacidad;
        this.capacidad = numero;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public Integer getCapacidad() { return numero; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
}
