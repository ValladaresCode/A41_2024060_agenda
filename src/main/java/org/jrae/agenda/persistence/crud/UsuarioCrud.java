package org.jrae.agenda.persistence.crud;

import org.jrae.agenda.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public class UsuarioCrud extends JpaRepository<Usuario, Integer> {
    
}
