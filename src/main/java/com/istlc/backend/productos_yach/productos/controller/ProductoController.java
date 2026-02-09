package com.istlc.backend.productos_yach.productos.controller;

import com.istlc.backend.productos_yach.productos.dto.ProductoCreateDto;
import com.istlc.backend.productos_yach.productos.dto.ProductoDto;
import com.istlc.backend.productos_yach.productos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDto crear(@Valid @RequestBody ProductoCreateDto dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<ProductoDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProductoDto obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}
