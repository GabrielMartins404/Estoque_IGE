package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.models.Requisitante;
import com.estoqueige.estoqueige.repositories.RequisitanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequisitanteServices {
    private final RequisitanteRepository requisitanteRepository;

    public RequisitanteServices(RequisitanteRepository requisitanteRepository) {
        this.requisitanteRepository = requisitanteRepository;
    }

    /* Método services */
    public Requisitante buscarRequisitantePorId(Long id) {
        Optional<Requisitante> requisitante = this.requisitanteRepository.findById(id);
        return requisitante.orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o requisitante"));
    }

    public List<Requisitante> buscarTodosRequisitantes() {
        List<Requisitante> requisitantes = this.requisitanteRepository.findAll();
        return requisitantes;
    }

    @Transactional
    public Requisitante cadastrarRequisitante(Requisitante requisitante) {
        requisitante.setReqId(null);
        return this.requisitanteRepository.save(requisitante);
    }

    @Transactional
    public Requisitante atualizarRequisitante(Requisitante requisitante) {
        Requisitante newRequisitante = this.buscarRequisitantePorId(requisitante.getReqId());

        //Copio todas as propriedades do requisitante para newRequisitante, menos o ID
        BeanUtils.copyProperties(requisitante, newRequisitante, "reqId");
        this.requisitanteRepository.save(newRequisitante);

        return newRequisitante;
    }

    @Transactional
    public Requisitante alterarStatusAtivoRequisitante(Long id) {
        Requisitante requisitante = this.requisitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requisitante não encontrado com o ID: " + id));

        requisitante.setIsAtivo(!requisitante.isAtivo());
        return this.requisitanteRepository.save(requisitante);
    }
}
