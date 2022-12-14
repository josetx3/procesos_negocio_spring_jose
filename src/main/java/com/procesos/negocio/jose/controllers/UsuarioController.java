package com.procesos.negocio.jose.controllers;

import com.procesos.negocio.jose.models.Usuario;
import com.procesos.negocio.jose.repository.UsuarioRepository;
import com.procesos.negocio.jose.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id) {
        return usuarioService.getUserById(id);

    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@Validated @RequestBody  Usuario usuario) {
        return usuarioService.createUser(usuario);

    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuario() {
        return usuarioService.allUsers();
    }

    @GetMapping("/usuario/{nombre}/{apellidos}")
    public ResponseEntity listaPorNombreApellidos(@PathVariable String nombre, @PathVariable String apellidos) {
        return usuarioService.allUsersByNameAndLastName(nombre,apellidos);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listaPorNombre(@PathVariable String nombre) {
        return usuarioService.allUsersByName(nombre);
    }

    @GetMapping("/usuario/apellidos/{apellidos}")
    public ResponseEntity listaPorApellidos(@PathVariable String apellidos) {
        return usuarioService.allUsersByLastName(apellidos);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @Validated @RequestBody  Usuario usuario) {
        return usuarioService.editUser(id, usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        return usuarioService.deleteUserById(id);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
