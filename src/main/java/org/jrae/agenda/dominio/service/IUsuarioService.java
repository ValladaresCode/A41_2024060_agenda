package org.jrae.agenda.dominio.service;

import org.jrae.agenda.persistence.entity.Contacto;
import org.jrae.agenda.persistence.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuarios();
    Contacto buscarUsuarioPorNombre(String nombre);
    void guardarUsuario(Contacto contacto);
    void eliminarUsuario(Contacto contacto);
}
