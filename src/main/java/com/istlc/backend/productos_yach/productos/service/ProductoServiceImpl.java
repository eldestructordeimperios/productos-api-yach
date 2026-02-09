package com.istlc.backend.productos_yach.productos.service;

import com.istlc.backend.productos_yach.categoria.entity.Categoria;
import com.istlc.backend.productos_yach.categoria.repository.CategoriaRepository;
import com.istlc.backend.productos_yach.common.exception.ResourceNotFoundException;
import com.istlc.backend.productos_yach.productos.dto.ProductoCreateDto;
import com.istlc.backend.productos_yach.productos.dto.ProductoDto;
import com.istlc.backend.productos_yach.productos.entity.Producto;
import com.istlc.backend.productos_yach.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepo;
    private final CategoriaRepository categoriaRepo;

    public ProductoServiceImpl(ProductoRepository productoRepo, CategoriaRepository categoriaRepo) {
        this.productoRepo = productoRepo;
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    @Transactional
    public ProductoDto crear(ProductoCreateDto dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setActivo(dto.getActivo());

        Set<Categoria> cats = new HashSet<>();
        if (dto.getCategorias() != null) {
            for (String nombreCat : dto.getCategorias()) {
                String limpio = nombreCat.trim();

                Categoria cat = categoriaRepo.findByNombreIgnoreCase(limpio)
                        .orElseGet(() -> categoriaRepo.save(new Categoria(limpio)));

                cats.add(cat);
            }
        }

        p.setCategorias(cats);
        Producto guardado = productoRepo.save(p);

        return toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> listar() {
        return productoRepo.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDto obtenerPorId(Long id) {
        Producto p = productoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no existe"));
        return toDto(p);
    }

    private ProductoDto toDto(Producto p) {
        List<String> categorias = p.getCategorias().stream()
                .map(Categoria::getNombre)
                .sorted()
                .collect(Collectors.toList());

        return new ProductoDto(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getActivo(),
                categorias
        );
    }
}
