package org.jrae.agenda.persistence.crud;

import org.jrae.agenda.persistence.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ContactoCrud extends JpaRepository<Contacto, Integer> {

}
