package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.models.Requisitante;
import com.estoqueige.estoqueige.repositories.RequisitanteRepository;
import com.estoqueige.estoqueige.services.exceptions.ErroAoBuscarObjetos;
import com.estoqueige.estoqueige.services.exceptions.ErroValidacaoLogica;

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
        return requisitante.orElseThrow(() -> new ErroAoBuscarObjetos("Não foi possivel encontrar o requisitante com id: "+id));
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
        if(requisitante.getFacRequisitante().getIsAtivo()){
            throw new ErroValidacaoLogica("Não é possível cadastrar requisitante com faculdade inativa");
        }
        this.requisitanteRepository.save(newRequisitante);

        return newRequisitante;
    }

    @Transactional
    public Requisitante alterarStatusAtivoRequisitante(Long id) {
        Requisitante requisitante = buscarRequisitantePorId(id);

        requisitante.setIsAtivo(!requisitante.getIsAtivo());
        return this.requisitanteRepository.save(requisitante);
    }
}
