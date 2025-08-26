package org.jrae.agenda;

import org.jrae.agenda.dominio.service.IContactoService;
import org.jrae.agenda.dominio.service.IUsuarioService;
import org.jrae.agenda.persistence.entity.Contacto;
import org.jrae.agenda.persistence.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AgendaApplication  implements CommandLineRunner {

    @Autowired
    private IContactoService contactoService;
    @Autowired
    private IUsuarioService usuarioService;

    private static final Logger logger = LoggerFactory.getLogger(AgendaApplication.class);

    String sl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Aqui inicia la app");
        SpringApplication.run(AgendaApplication.class, args);
        logger.info("Aqui finaliza la app");
	}

    @Override
    public void run(String... args) throws Exception {
        login();
    }


    private void agendaAPP(){
        logger.info("------------APP de Agenda de contactos ------------------");
        var salir = false;
        var consola = new Scanner(System.in);
        while (!salir){
            var opcion = mostrarMenu(consola);
            salir = ejecutarOpciones(consola, opcion);
            logger.info(sl);
        }
    }

    private void login(){
        int acceso = 1;
        var consola = new Scanner(System.in);
        do{
            logger.info("""
                    \n Escribir el numero de la opcion deseada
                    1. Iniciar Sesion
                    2. Crear cuenta
                    3. Salir de la APP
                    """);
            var ingreso = Integer.parseInt(consola.nextLine());

            switch (ingreso){
                case 1 ->{
                    logger.info(sl);
                    logger.info("Iniciar Sesion");
                    logger.info("Escribir su Usuario");
                    var user = consola.nextLine();
                    logger.info("Escribir su Contrasena");
                    var password = consola.nextLine();

                    List<Usuario> usuarios = usuarioService.listarUsuarios();

                    for (Usuario u : usuarios){
                        if (u.getApodo().equals(user) && u.getContrasena().equals(password)){
                            logger.info(sl);
                            agendaAPP();
                            acceso = 3;
                        }else{

                            logger.info("Usuario o Contrasena incorrecto");
                            acceso = 1;
                        }
                    }
                }
                case 2 -> {
                    logger.info(sl);
                    logger.info("Crear cuenta");
                    logger.info("Ingrese el Usuario:");
                    var nombre = consola.nextLine();
                    logger.info("Ingrese la Contrasena:");
                    var contrasena = consola.nextLine();
                    var usuario = new Usuario();
                    usuario.setApodo(nombre);
                    usuario.setContrasena(contrasena);
                    usuarioService.guardarUsuario(usuario);
                    logger.info(sl);
                    logger.info("Usuario agregado exitosamente");
                }

                case 3 ->{
                    logger.info(sl);
                    logger.info("Saliendo ...");
                    acceso = 3;
                }

                default -> {
                    logger.info(sl);
                    logger.info("Opcion invalida");
                }
            }

        } while(acceso!= 3);

    }

    private int mostrarMenu(Scanner consola){
        logger.info("""
                \n -----APP------
                1. Listar Contactos
                2. Buscar Contactos por nombre
                3. Agregar nuevo Contacto
                4. Modificar Contacto
                5. Eliminar Contacto
                6. Regresar al LOGIN
                Elije la opcion que desee\s""");
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion){
        var salir = false;
        switch (opcion){
            case 1 ->{
                logger.info(sl);
                logger.info("Listar Contactos");
                List<Contacto> contactos = contactoService.listarContactos();
                contactos.forEach(contacto -> logger.info(contacto.toString()+sl));
            }

            case 2 ->{
                logger.info(sl);
                logger.info("Buscar Contactos por nombre");
                var nombre = consola.nextLine();
                Contacto contacto = contactoService.buscarClientePorNombre(nombre);
                if (contacto != null) {
                    logger.info(contacto.toString() + sl);
                } else {
                    logger.info("No se encontró ningún contacto con ese nombre.");
                }
            }

            case 3 ->{
                logger.info(sl);
                logger.info("Agregar Contacto");
                logger.info("Ingrese el nombre:");
                var nombre = consola.nextLine();
                logger.info("Ingrese el telefono:");
                var telefono = consola.nextLine();
                logger.info("Ingrese el correo:");
                var correo = consola.nextLine();
                var contacto = new Contacto();
                contacto.setNombre(nombre);
                contacto.setTelefono(telefono);
                contacto.setCorreo(correo);
                contactoService.guardarContacto(contacto);
                logger.info("Contacto agregado exitosamente");
            }

            case 4 ->{
                logger.info(sl);
                logger.info("Modificar Contactos");
                logger.info("Ingrese nombre del contacto para editar");
                var nombre = consola.nextLine();
                Contacto contacto = contactoService.buscarClientePorNombre(nombre);
                if (contacto != null){
                    logger.info("Ingrese el nombre:");
                    nombre = consola.nextLine();
                    logger.info("Ingrese el telefono:");
                    var telefono = consola.nextLine();
                    logger.info("Ingrese el correo:");
                    var correo = consola.nextLine();
                    contacto.setNombre(nombre);
                    contacto.setTelefono(telefono);
                    contacto.setCorreo(correo);
                    contactoService.guardarContacto(contacto);
                    logger.info("Contacto editado exitosamente");
                }else{
                    logger.info("contacto invalido"+sl+contacto+sl);
                }
            }

            case 5 ->{
                logger.info(sl);
                logger.info("Eliminar Contactos");
                logger.info("Ingrese nombre del contacto para Eliminar");
                var nombre = consola.nextLine();
                Contacto contacto = contactoService.buscarClientePorNombre(nombre);
                if (contacto != null) {
                    contactoService.eliminarContacto(contacto);
                    logger.info(sl+"Contacto eliminado "+contacto+sl);
                }else{
                    logger.info("Contacto No encontrado "+ contacto);
                }
            }

            case 6 ->{
                logger.info(sl);
                logger.info("Saliendo..."+sl+sl);
                salir = true;
            }
            default ->  logger.info("Opcion invalida");
        }
        return salir;
    }

}
