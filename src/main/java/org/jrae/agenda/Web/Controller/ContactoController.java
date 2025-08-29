package org.jrae.agenda.Web.Controller;


import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;

import org.jrae.agenda.dominio.service.IContactoService;
import org.jrae.agenda.dominio.service.IUsuarioService;
import org.jrae.agenda.persistence.entity.Contacto;
import org.jrae.agenda.persistence.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class ContactoController {
    @Autowired
    IContactoService contactoService;
    private List<Contacto> contactos;
    private Contacto contactoSeleccionado;

    private static final Logger logger = LoggerFactory.getLogger(ContactoController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.contactos = this.contactoService.listarContactos();
        this.contactos.forEach(contacto -> logger.info(contacto.toString()));
    }

    public void agregarContacto(){
        this.contactoSeleccionado = new Contacto();

    }
}
