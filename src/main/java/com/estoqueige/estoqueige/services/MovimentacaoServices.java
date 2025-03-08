package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.dto.MovimentacaoDto;
import com.estoqueige.estoqueige.dto.ProdutoMovimentacaoDto;
import com.estoqueige.estoqueige.models.Movimentacao;
import com.estoqueige.estoqueige.models.ProdutoMovimentacao;
import com.estoqueige.estoqueige.repositories.MovimentacaoRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoServices {
    private final MovimentacaoRepository movimentacaoRepository;

    private final MovimentacaoEstoqueServices movimentacaoEstoqueServices;

    private final ProdutoServices produtoServices;

    public MovimentacaoServices(MovimentacaoRepository movimentacaoRepository, MovimentacaoEstoqueServices movimentacaoEstoqueServices, ProdutoServices produtoServices) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoEstoqueServices = movimentacaoEstoqueServices;
        this.produtoServices = produtoServices;
    }


    /* Método services */
    //Como ProdutoMovimentacao é uma classe que depende de Movimentacao, esse método é implementado aqui. Mas pode ser alterado mais para frente
    public ProdutoMovimentacaoDto gerarProdutoMovimentacaoDto(ProdutoMovimentacao produtoMovimentacao){
        ProdutoMovimentacaoDto produtoMovimentacaoDto = new ProdutoMovimentacaoDto();

        //Aqui converto o produtoMovimentacao em produtoDto, para simplificar a apresentação das movimentacoes
        produtoMovimentacaoDto.setProduto(
            this.produtoServices.gerarProdutoDto(produtoMovimentacao.getProMovProduto()
        ));

        produtoMovimentacaoDto.setQtdProduto(produtoMovimentacao.getProMovQtdProduto());

        return produtoMovimentacaoDto;
    }

    public MovimentacaoDto gerarMovimentacaoDto(Movimentacao movimentacao){
        MovimentacaoDto movimentacaoDto = new MovimentacaoDto();

        List<ProdutoMovimentacaoDto> produtoMovimentacaoDtos = new ArrayList<>();
        for (ProdutoMovimentacao produtoMovimentacao : movimentacao.getProdutosMov()) {
            //Aqui, chamo a função acima que insere um array de ProdutoMovimentacao com ProdutoDto
            produtoMovimentacaoDtos.add(gerarProdutoMovimentacaoDto(produtoMovimentacao));
        }

        movimentacaoDto.setMovId(movimentacao.getMovId());
        movimentacaoDto.setMovData(movimentacao.getMovData());
        movimentacaoDto.setMovDataCancelamento(movimentacao.getMovDataCancelamento());
        movimentacaoDto.setMovOrigem(movimentacao.getMovOrigem());
        movimentacaoDto.setMovTipo(movimentacao.getMovTipo());
        movimentacaoDto.setMovStatus(movimentacao.getMovStatus());
        movimentacaoDto.setMovRequisitante(movimentacao.getMovRequisitante());
        movimentacaoDto.setMovUsuario(movimentacao.getMovUsuario());
        movimentacaoDto.setProdutosMov(produtoMovimentacaoDtos);
        return movimentacaoDto;
        
    }

    //Esse método irá ser usado no frontEnd onde preciso que retorne somente uma movimentacao DTO
    public MovimentacaoDto retornarMovimentacaoDto(Long id){
        Movimentacao movimentacao = this.buscarMovimentacaoPorId(id);
        MovimentacaoDto movimentacaoDto = gerarMovimentacaoDto(movimentacao);

        return movimentacaoDto;
    }

    // public MovimentacaoSemProdutosDto retornarMovimentacaoSemProdutosDto(Movimentacao movimentacao){

    // }

    public Movimentacao buscarMovimentacaoPorId(Long id){
        Movimentacao movimentacao = this.movimentacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Falha ao buscar Movimentacao"));

        return movimentacao;
    }

    public List<MovimentacaoDto> buscarTodasMovimentacoes(){
        List<Movimentacao> movimentacoes = this.movimentacaoRepository.findAll();
        List<MovimentacaoDto> movimentacaoDtos = new ArrayList<>();
        
        for (Movimentacao movimentacao : movimentacoes) {
            movimentacaoDtos.add(gerarMovimentacaoDto(movimentacao));
        }

        return movimentacaoDtos;
    }

    //Método para salvar a movimentação no Banco de Dados
    @Transactional
    public void salvarMovimentacao(Movimentacao movimentacao) {
        //Adicionar validação

        //Salvar a movimentação no banco de dados
        movimentacao.setMovId(null);
        movimentacao.setMovStatus("F");
        movimentacao.setMovData(LocalDate.now());

        //É preciso fazer o vinculo da movimentação para os ProdutosMovimentações. Desse modo, é preciso fazer o loop abaixo
        for (ProdutoMovimentacao produtoMovimentacao : movimentacao.getProdutosMov()) {
            produtoMovimentacao.setProMovMovimentacao(movimentacao);
        }

        movimentacao = movimentacaoRepository.save(movimentacao);


        for (ProdutoMovimentacao produtoMovimentacao: movimentacao.getProdutosMov()){
            movimentacaoEstoqueServices.salvarMovimentacaoEstoque(movimentacao, produtoMovimentacao);
        }
    }

    @Transactional
    public Movimentacao atualizarMovimentacao(Movimentacao movimentacao){
        Movimentacao newMovimentacao = this.buscarMovimentacaoPorId(movimentacao.getMovId());
        BeanUtils.copyProperties(movimentacao, newMovimentacao, "getMovId");

        return this.movimentacaoRepository.save(newMovimentacao);
    }

    @Transactional
    public void cancelarMovimentacao(Movimentacao movimentacao){

        movimentacao.setMovStatus("C");
        this.atualizarMovimentacao(movimentacao);
        
        for (ProdutoMovimentacao produtoMovimentacao : movimentacao.getProdutosMov()) {
            this.movimentacaoEstoqueServices.salvarMovimentacaoEstoque(movimentacao, produtoMovimentacao);
        }
    }
}
