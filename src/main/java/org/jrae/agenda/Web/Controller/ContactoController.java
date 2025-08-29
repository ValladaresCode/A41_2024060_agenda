package org.jrae.agenda.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;

import org.jrae.agenda.dominio.service.IContactoService;
import org.jrae.agenda.persistence.entity.Contacto;
import org.primefaces.PrimeFaces;
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
    private IContactoService contactoService;

    private List<Contacto> contactos;
    private Contacto contactoSeleccionado;

    private static final Logger logger = LoggerFactory.getLogger(ContactoController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    // Cargar la lista de contactos desde el servicio
    public void cargarDatos(){
        this.contactos = this.contactoService.listarContactos();
        this.contactos.forEach(contacto -> logger.info(contacto.toString()));
    }

    public void agregarContacto(){
        this.contactoSeleccionado = new Contacto();
    }

    public void guardarContacto(){
        logger.info("Contacto a guardar: "+this.contactoSeleccionado);

        // Agregar (Insertar)
        if (this.contactoSeleccionado.getCodigoContacto() == null){
            this.contactoService.guardarContacto(this.contactoSeleccionado);  // Usar servicio de contacto
            this.contactos.add(this.contactoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Contacto agregado"));
        }
        // Modificar (Actualizar)
        else{
            this.contactoService.guardarContacto(this.contactoSeleccionado);  // Usar servicio de contacto
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Contacto actualizado"));
        }

        // Ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalContacto').hide()");

        // Actualizar la tabla con AJAX
        PrimeFaces.current().ajax().update("formulario-contactos:mensaje_emergente",
                "formulario-contactos:tabla-contactos");

        // Reset del contacto seleccionado
        this.contactoSeleccionado = null;
    }

    public void eliminarContacto(){
        logger.info("Contacto a eliminar: "+contactoSeleccionado);
        this.contactoService.eliminarContacto(contactoSeleccionado);
        this.contactos.remove(contactoSeleccionado);
        this.contactoSeleccionado = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contacto eliminado"));
        PrimeFaces.current().ajax().update("formulario-contactos:mensaje_emergente",
                "formulario-contactos:tabla-contactos");
    }
}

