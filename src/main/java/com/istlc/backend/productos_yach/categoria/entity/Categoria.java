package com.istlc.backend.productos_yach.categoria.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorias", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre")
})
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nombre;

    // mappedBy apunta al nombre del atributo en Producto
    @ManyToMany(mappedBy = "categorias")
    private Set<com.istlc.backend.productos_yach.productos.entity.Producto> productos = new HashSet<>();

    public Categoria() {}

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<com.istlc.backend.productos_yach.productos.entity.Producto> getProductos() { return productos; }
}
