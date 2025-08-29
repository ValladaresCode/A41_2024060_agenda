package org.jrae.agenda.Web.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;

import org.jrae.agenda.dominio.service.IUsuarioService;
import org.jrae.agenda.persistence.entity.Usuario;
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
}
