package com.roky.service;


import com.roky.model.Producto;
import com.roky.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final ProductoRepository productoRepository;

    @Autowired
    public InventarioService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto crearProducto(Producto nuevoProducto) {
        return productoRepository.save(nuevoProducto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        if (productoRepository.existsById(id)) {
            productoActualizado.setId(id);
            return productoRepository.save(productoActualizado);
        } else {
            //  Devolver null si el producto no existe
            return null;
        }
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }





}
