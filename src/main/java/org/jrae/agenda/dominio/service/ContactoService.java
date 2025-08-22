package org.jrae.agenda.dominio.service;

import org.jrae.agenda.persistence.crud.ContactoCrud;
import org.jrae.agenda.persistence.entity.Contacto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContactoService implements  IContactoService{
    @Autowired
    private ContactoCrud crud;

    private  static final Logger logger = LoggerFactory.getLogger(ContactoService.class);

    String sl = System.lineSeparator();

    @Override
    public List<Contacto> listarContactos() {
        List<Contacto> contactos = crud.findAll();
        return contactos;
    }

    @Override
    public Contacto buscarClientePorNombre(String nombre) {
        List<Contacto> contactos = crud.findAll();
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void guardarContacto(Contacto contacto) {
        crud.save(contacto);
    }

    @Override
    public void eliminarContacto(Contacto contacto) {
        crud.delete(contacto);
    }
}
