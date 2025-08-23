package org.jrae.agenda.dominio.service;

import org.jrae.agenda.persistence.crud.UsuarioCrud;
import org.jrae.agenda.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioCrud crud;

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = crud.findAll();
        return usuarios;
    }

    @Override
    public Usuario buscarUsuarioPorNombre(String nombre) {
        return null;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {

    }

    @Override
    public void eliminarUsuario(Usuario usuario) {

    }
}
