package ingsoftware.evaluacion2.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "variantes")
public class Variante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private double aumentoPrecio; 

    public Variante(Long id, String nombre, double aumentoPrecio) {
        this.id = id;
        this.nombre = nombre;
        this.aumentoPrecio = aumentoPrecio;
    }

    public Variante(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getAumentoPrecio() {
        return aumentoPrecio;
    }

    public void setAumentoPrecio(double aumentoPrecio) {
        this.aumentoPrecio = aumentoPrecio;
    }
}
