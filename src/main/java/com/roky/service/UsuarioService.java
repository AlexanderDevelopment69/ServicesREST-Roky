package com.roky.service;

import com.roky.model.Cliente;
import com.roky.model.Personal;

import com.roky.repository.ClienteRepository;
import com.roky.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final ClienteRepository clienteRepository;
    private final PersonalRepository personalRepository;

    @Autowired
    public UsuarioService(ClienteRepository clienteRepository, PersonalRepository personalRepository) {
        this.clienteRepository = clienteRepository;
        this.personalRepository = personalRepository;
    }

    // Métodos para Cliente
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente crearCliente(Cliente nuevoCliente) {
        return clienteRepository.save(nuevoCliente);
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        if (clienteRepository.existsById(id)) {
            clienteActualizado.setId(id);
            return clienteRepository.save(clienteActualizado);
        } else {
            // Devolver null si el cliente no existe
            return null;
        }
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Métodos para Personal
    public List<Personal> obtenerTodoElPersonal() {
        return personalRepository.findAll();
    }

    public Personal crearPersonal(Personal nuevoPersonal) {
        return personalRepository.save(nuevoPersonal);
    }

    public Optional<Personal> obtenerPersonalPorId(Long id) {
        return personalRepository.findById(id);
    }

    public Personal actualizarPersonal(Long id, Personal personalActualizado) {
        if (personalRepository.existsById(id)) {
            personalActualizado.setId(id);
            return personalRepository.save(personalActualizado);
        } else {
            // Devolver null si el personal no existe
            return null;
        }
    }

    public void eliminarPersonal(Long id) {
        personalRepository.deleteById(id);
    }

}
