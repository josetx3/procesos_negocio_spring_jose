package com.procesos.negocio.jose.controllers;

import com.procesos.negocio.jose.models.Usuario;
import com.procesos.negocio.jose.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository UsuarioRepository;
    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = UsuarioRepository.findById(id);
        return usuario;
    }

    @PostMapping(value = "/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        UsuarioRepository.save(usuario);
        return usuario;
    }

    @GetMapping(value = "/usuarios")
    public List<Usuario> listarUsuarios(){
        return UsuarioRepository.findAll();

    }

    @GetMapping("/usuario/{nombre}/{apellidos}")
    public List<Usuario> listarPorNombreApellido(@PathVariable String nombre,
                                                 @PathVariable String apellidos){
        return UsuarioRepository.findAllByNombreAndApellidos(nombre,apellidos);
    }

    @GetMapping("/usuario/apellidos/{apellidos}")
    public  List<Usuario> listarPorApellido(@PathVariable String apellidos){
        return  UsuarioRepository.findAllByApellidos(apellidos);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public List<Usuario> listarPorNombre(@PathVariable String nombre){
        return  UsuarioRepository.findAllByNombre(nombre);
    }

    @PutMapping("/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioBD = UsuarioRepository.findById(id).get();
        try {
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellidos(usuario.getApellidos());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioBD.setTelefono(usuario.getTelefono());
            UsuarioRepository.save(usuarioBD);
            return usuarioBD;
        }catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id){
        Usuario usuarioBD = UsuarioRepository.findById(id).get();
        try {
            UsuarioRepository.delete(usuarioBD);
            return usuarioBD;
        } catch (Exception e){
            return null;
        }
    }

}
