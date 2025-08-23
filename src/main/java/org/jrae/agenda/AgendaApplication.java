package org.jrae.agenda;

import org.jrae.agenda.dominio.service.IContactoService;
import org.jrae.agenda.dominio.service.IUsuarioService;
import org.jrae.agenda.persistence.entity.Contacto;
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
        agendaAPP();
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

    private int mostrarMenu(Scanner consola){
        logger.info("""
                \n -----APP------
                1. Listar Contactos
                2. Buscar Contactos por nombre
                3. Agregar nuevo Contacto
                4. Modificar Contacto
                5. Eliminar Contacto
                6. Salir
                Elije la opcion que desee\s""");
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion){
        var salir = false;
        switch (opcion){
            case 1 ->{
                logger.info("Listar Contactos");
                List<Contacto> contactos = contactoService.listarContactos();
                contactos.forEach(contacto -> logger.info(contacto.toString()+sl));
            }

            case 2 ->{
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
                logger.info("Modificar Contactos");
                logger.info("Ingrese nombre del contacto para editar");
                var nombre = consola.nextLine();
                Contacto contacto = contactoService.buscarClientePorNombre(nombre);
                if (contacto != null) {
                    contactoService.eliminarContacto(contacto);
                    logger.info(sl+"Contacto agregado"+contacto+sl);
                }else{
                    logger.info("Contacto No encontrado "+ contacto);
                }
            }

            case 6 ->{
                logger.info("Saliendo..."+sl+sl);
                salir = true;
            }

            default ->  logger.info("Opcion invalida");
        }
        return salir;
    }

}
