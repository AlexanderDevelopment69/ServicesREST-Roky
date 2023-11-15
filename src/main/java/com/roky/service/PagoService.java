package com.roky.service;


import com.roky.model.Pago;
import com.roky.model.Pedido;
import com.roky.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final PedidoService pedidoService;

    @Autowired
    public PagoService(PagoRepository pagoRepository, PedidoService pedidoService) {
        this.pagoRepository = pagoRepository;
        this.pedidoService = pedidoService;
    }

    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }



    public Optional<Pago> obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago crearPago(Pago nuevoPago) {
        // Obtener el pedido asociado al pago
        Pedido pedidoAsociado = nuevoPago.getPedido();

        // Verificar si el pedido existe
        if (pedidoAsociado != null) {
            Pedido pedido = pedidoService.obtenerPedidoPorId(pedidoAsociado.getId())
                    .orElseThrow(() -> new RuntimeException("No se pudo encontrar el pedido con ID: " + pedidoAsociado.getId()));

            // Asociar el pedido al pago
            nuevoPago.setPedido(pedido);
        }

        // Guardar el pago
        return pagoRepository.save(nuevoPago);
    }

    public Pago actualizarPago(Long id, Pago pagoActualizado) {
        if (pagoRepository.existsById(id)) {
            pagoActualizado.setId(id);
            return pagoRepository.save(pagoActualizado);
        } else {
            throw new RuntimeException("No se pudo encontrar el pago con ID: " + id);
        }
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}
