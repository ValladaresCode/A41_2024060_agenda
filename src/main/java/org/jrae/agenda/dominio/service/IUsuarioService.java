package org.jrae.agenda.dominio.service;

import org.jrae.agenda.persistence.entity.Contacto;
import org.jrae.agenda.persistence.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorNombre(String nombre);
    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
}
