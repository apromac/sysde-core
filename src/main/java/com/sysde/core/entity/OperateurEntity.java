package com.sysde.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;

@Data
@Entity
@Table(schema = "core", name = "operateur")
public class OperateurEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operateur", updatable = false, nullable = false)
    private Long operateurID;

    @Column(name = "rs_operateur", nullable = false)
    private String rsOperateur;

    @Column(name = "acronyme_operateur", nullable = false)
    private String acronymeOperateur;

    @Column(name = "adr_operateur")
    private String adresseOperateur;

    @Column(name = "email_operateur")
    private String emailOperateur;

    @Column(name = "tel_operateur")
    private String telOperateur;

    @Column(name = "encadreur", nullable = false)
    private Boolean encadreur;
}

