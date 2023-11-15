package com.roky.controller;

import com.roky.model.Cliente;
import com.roky.model.Personal;
import com.roky.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoints para Cliente
    @GetMapping("/clientes")
    public List<Cliente> obtenerTodosLosClientes() {
        return usuarioService.obtenerTodosLosClientes();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = usuarioService.obtenerClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/clientes/crear")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente nuevoCliente) {
        Cliente clienteCreado = usuarioService.crearCliente(nuevoCliente);
        return ResponseEntity.ok(clienteCreado);
    }

    @PutMapping("/clientes/actualizar/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        Cliente cliente = usuarioService.actualizarCliente(id, clienteActualizado);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/clientes/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        usuarioService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints para Personal
    @GetMapping("/personal")
    public List<Personal> obtenerTodoElPersonal() {
        return usuarioService.obtenerTodoElPersonal();
    }

    @GetMapping("/personal/{id}")
    public ResponseEntity<Personal> obtenerPersonalPorId(@PathVariable Long id) {
        Optional<Personal> personal = usuarioService.obtenerPersonalPorId(id);
        return personal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/personal/crear")
    public ResponseEntity<Personal> crearPersonal(@RequestBody Personal nuevoPersonal) {
        Personal personalCreado = usuarioService.crearPersonal(nuevoPersonal);
        return ResponseEntity.ok(personalCreado);
    }

    @PutMapping("/personal/actualizar/{id}")
    public ResponseEntity<Personal> actualizarPersonal(@PathVariable Long id, @RequestBody Personal personalActualizado) {
        Personal personal = usuarioService.actualizarPersonal(id, personalActualizado);
        return personal != null ? ResponseEntity.ok(personal) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/personal/eliminar/{id}")
    public ResponseEntity<Void> eliminarPersonal(@PathVariable Long id) {
        usuarioService.eliminarPersonal(id);
        return ResponseEntity.noContent().build();
    }

}
