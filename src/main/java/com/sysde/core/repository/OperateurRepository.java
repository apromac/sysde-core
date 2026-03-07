package com.sysde.core.repository;

import com.sysde.core.entity.OperateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperateurRepository extends JpaRepository<OperateurEntity, Long> {

    /**
     * Requette permettant de retourner la liste des opérateurs encadreurs. Cela signifie que l'attribut 'encadreur' est à 'True'
     * @return la liste des opérateurs encadreurs
     */
    List<OperateurEntity> findByEncadreurTrue();

    /**
     * Requette permettant de retourner la liste des opérateurs non encadreurs. Cela signifie que l'attribut 'encadreur' est à 'False'
     * @return la liste des opérateur non encadreurs
     */
    List<OperateurEntity> findByEncadreurFalse();
}
