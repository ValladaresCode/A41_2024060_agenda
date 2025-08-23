package org.jrae.agenda.persistence.crud;

import org.jrae.agenda.persistence.entity.Contacto;
import org.jrae.agenda.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioCrud extends JpaRepository<Usuario, Integer> {

}
