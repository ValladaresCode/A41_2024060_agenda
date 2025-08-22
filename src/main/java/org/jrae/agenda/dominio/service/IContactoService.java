package org.jrae.agenda.dominio.service;

import org.jrae.agenda.persistence.entity.Contacto;

import java.util.List;

public interface IContactoService {
    List<Contacto>listarContactos();
    Contacto buscarClientePorNombre(String nombre);
    void guardarContacto(Contacto contacto);
    void eliminarContacto(Contacto contacto);

}
