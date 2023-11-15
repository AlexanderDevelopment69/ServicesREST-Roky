package com.roky.service;


import com.roky.model.Pedido;
import com.roky.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {


    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido crearPedido(Pedido nuevoPedido) {
        return pedidoRepository.save(nuevoPedido);
    }

    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
        if (pedidoRepository.existsById(id)) {
            pedidoActualizado.setId(id);
            return pedidoRepository.save(pedidoActualizado);
        } else {
            //  Devolver null si el menu si el pedido no existe
            return null;
        }
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
