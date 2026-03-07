package com.sysde.core.serviceimpl;

import com.sysde.core.entity.OperateurEntity;
import com.sysde.core.repository.OperateurRepository;
import com.sysde.core.service.OperateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperateurServiceImpl implements OperateurService {

    private final OperateurRepository operateurRepository;



    @Override
    public List<OperateurEntity> findByOperateurEncadreur() {
        return operateurRepository.findByEncadreurTrue();
    }

    @Override
    public List<OperateurEntity> findByOperateurNonEncadreur() {
        return operateurRepository.findByEncadreurFalse();
    }

    @Override
    public List<OperateurEntity> findByOperateurs() {
        return operateurRepository.findAll();
    }

    @Override
    public OperateurEntity findOperateurByID(Long operateurID) {
        return operateurRepository.findById(operateurID).orElse(null);
    }

    @Override
    public OperateurEntity saveOperateur(OperateurEntity operateur) {
        return operateurRepository.save(operateur);
    }

    @Override
    public OperateurEntity updateOperateur(Long operateurID, OperateurEntity operateurEntity) {
        return null;
    }
}
