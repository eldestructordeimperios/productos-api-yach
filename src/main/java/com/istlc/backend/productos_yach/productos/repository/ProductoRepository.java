package com.istlc.backend.productos_yach.productos.repository;

import com.istlc.backend.productos_yach.productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
