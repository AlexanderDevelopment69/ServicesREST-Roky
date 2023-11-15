package com.roky.service;

import com.roky.model.Menu;
import com.roky.model.Producto;
import com.roky.repository.MenuRepository;
import com.roky.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    private final ProductoRepository productoRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, ProductoRepository productoRepository) {
        this.menuRepository = menuRepository;
        this.productoRepository = productoRepository;
    }

    public List<Menu> obtenerTodosLosMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> obtenerMenuPorId(Long id) {
        return menuRepository.findById(id);
    }

    public Menu crearMenu(Menu menuConProductos) {
        // Obtener la lista de productos por sus IDs
        List<Producto> productos = productoRepository.findAllById(
                menuConProductos.getProductos().stream()
                        .map(Producto::getId)
                        .collect(Collectors.toList())
        );

        // Asociar productos al menú
        menuConProductos.setProductos(productos);

        // Guardar el nuevo menu con productos en la base de datos
        return menuRepository.save(menuConProductos);
    }

    public Menu actualizarMenu(Long id, Menu menuActualizado) {
        Optional<Menu> menuExistente = menuRepository.findById(id);
        if (menuExistente.isPresent()) {
            menuActualizado.setId(id);
            return menuRepository.save(menuActualizado);
        }
        return null;  // Devolver null si el menu no existe
    }

    public void eliminarMenu(Long id) {
        menuRepository.deleteById(id);
    }


//    public void eliminarMenu(Long id) {
//        if (menuRepository.existsById(id)) {
//            menuRepository.deleteById(id);
//        } else {
//            throw new RuntimeException("No se pudo encontrar el menú con ID: " + id);
//        }
//    }
}