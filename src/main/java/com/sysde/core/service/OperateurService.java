package com.sysde.core.service;

import com.sysde.core.entity.OperateurEntity;

import java.util.List;

public interface OperateurService {

    /**
     *
     * @return
     */
    List<OperateurEntity> findByOperateurEncadreur();

    /**
     *
     * @return
     */
    List<OperateurEntity> findByOperateurNonEncadreur();

    /**
     *
     * @return
     */
    List<OperateurEntity> findByOperateurs();

    /**
     *
     * @param operateurID
     * @return
     */
    OperateurEntity findOperateurByID(Long operateurID);

    /**
     *
     * @param operateurEntity
     * @return
     */
    OperateurEntity saveOperateur(OperateurEntity operateurEntity);

    /**
     *
     * @param operateurID
     * @param operateurEntity
     * @return
     */
    OperateurEntity updateOperateur(Long operateurID, OperateurEntity operateurEntity);
}
