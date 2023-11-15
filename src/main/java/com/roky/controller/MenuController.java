package com.roky.controller;

import com.roky.model.Menu;
import com.roky.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> obtenerTodosLosMenus() {
        return menuService.obtenerTodosLosMenus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerMenuPorId(@PathVariable Long id) {
        Optional<Menu> menu = menuService.obtenerMenuPorId(id);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Menu> crearMenu(@RequestBody Menu nuevoMenu) {
        Menu menuCreado = menuService.crearMenu(nuevoMenu);
        return ResponseEntity.ok(menuCreado);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Menu> actualizarMenu(@PathVariable Long id, @RequestBody Menu menuActualizado) {
        Menu menu = menuService.actualizarMenu(id, menuActualizado);
        return menu != null ? ResponseEntity.ok(menu) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
        menuService.eliminarMenu(id);
        return ResponseEntity.noContent().build();
    }


//    @DeleteMapping("/eliminar/{id}")
//    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
//        try {
//            menuService.eliminarMenu(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (RuntimeException e) {
//            // Captura y relanza la excepción con codigo de estado NOT_FOUND
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
//        }
//    }


}
