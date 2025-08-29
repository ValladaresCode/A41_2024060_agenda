package org.jrae.agenda.web.controller;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;

import org.jrae.agenda.dominio.service.IUsuarioService;
import org.jrae.agenda.persistence.entity.Usuario;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class UsuarioController {
    @Autowired
    IUsuarioService usuarioService;
    private List<Usuario> usuarios;
    private Usuario usuarioSeleccionado;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.usuarios = this.usuarioService.listarUsuarios();
        this.usuarios.forEach(usuario -> logger.info(usuario.toString()));
    }

    public void agregarUsuario(){
        this.usuarioSeleccionado = new Usuario();

    }

    public void guardarUsuario(){
        logger.info("Usuario a guardar: "+this.usuarioSeleccionado);
        //Agregar(Insertar)
        if (this.usuarioSeleccionado.getCodigoUsuario() == null){
            this.usuarioService.guardarUsuario(this.usuarioSeleccionado);
            this.usuarios.add(this.usuarioSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario agregar"));

        }
        //Mpdificar(Update)
        else{
            this.usuarioService.guardarUsuario(this.usuarioSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario Actualizado"));
        }

        //Ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");

        //Actualiar la tabla con AJAX
        PrimeFaces.current().ajax().update("formulario-usuarios:mensaje_emergente",
                "formulario-usuarios:tabla-usuarios");

        //Reset del usuarioSeleccionado
        this.usuarioSeleccionado = null;
    }

    public void eliminarUsuario(){
        //Mostrar en consola
        logger.info("Usuario a eliminar: "+usuarioSeleccionado);
        //llamar a nuestro servicio de eliminacion de Cliente
        this.usuarioService.eliminarUsuario(usuarioSeleccionado);
        //Eliminarlo de la lista clientes
        this.usuarios.remove(usuarioSeleccionado);
        //limpiar usuarioSeleccionado
        this.usuarioSeleccionado = null;
        //enviar mnsaje emergente
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("usuario eliminado") );
        //Metodo ajax para actualizar la tabla
        PrimeFaces.current().ajax().update("formulario-usuarios:mensaje_emergente",
                "formulario-usuarios:tabla-usuarios");
    }
}
