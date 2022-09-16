package com.procesos.negocio.jose.controllers;

import com.procesos.negocio.jose.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UsuarioController {

    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setNombre("Jose");
        usuario.setApellidos("Quintero");
        usuario.setDocumento("1004945023");
        usuario.setDireccion("craaa");
        usuario.setTelefono("3183843124");
        usuario.setFechaNacimiento(new Date(2001,10,11));
        return usuario;
    }
}
