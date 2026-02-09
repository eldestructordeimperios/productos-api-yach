package com.istlc.backend.productos_yach.productos.service;

import com.istlc.backend.productos_yach.productos.dto.ProductoCreateDto;
import com.istlc.backend.productos_yach.productos.dto.ProductoDto;

import java.util.List;

public interface ProductoService {
    ProductoDto crear(ProductoCreateDto dto);
    List<ProductoDto> listar();
    ProductoDto obtenerPorId(Long id);
}
