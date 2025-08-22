package org.jrae.agenda.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Usuarios")

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoUsuario;

    @Column
    private String apodo;
    private String contrasena;
}
