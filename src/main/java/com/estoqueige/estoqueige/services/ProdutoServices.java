package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.dto.MovEstoqueDto;
import com.estoqueige.estoqueige.dto.ProdutoDto;
import com.estoqueige.estoqueige.models.CategoriaProduto;
import com.estoqueige.estoqueige.models.MovimentacaoEstoque;
import com.estoqueige.estoqueige.models.Produto;
import com.estoqueige.estoqueige.models.UnidadeProduto;
import com.estoqueige.estoqueige.repositories.CategoriaProdutoRepository;
import com.estoqueige.estoqueige.repositories.ProdutoRepository;
import com.estoqueige.estoqueige.repositories.UnidadeProdutoRepository;
import com.estoqueige.estoqueige.services.exceptions.ErroAoBuscarObjetos;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServices {

    private final CategoriaProdutoRepository categoriaProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final UnidadeProdutoRepository unidadeProdutoRepository;
    
    public ProdutoServices(ProdutoRepository produtoRepository, CategoriaProdutoRepository categoriaProdutoRepository, UnidadeProdutoRepository unidadeProdutoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaProdutoRepository = categoriaProdutoRepository;
        this.unidadeProdutoRepository = unidadeProdutoRepository;
    }


    /* Método dos services */

    public MovEstoqueDto gerarMovEstoqueDto(MovimentacaoEstoque movimentacaoEstoque){
        MovEstoqueDto movEstoqueDto = new MovEstoqueDto();
        
        movEstoqueDto.setMovEstId(movimentacaoEstoque.getMovEstId());
        movEstoqueDto.setMovEstData(movimentacaoEstoque.getMovEstData());
        movEstoqueDto.setMovEstQtd(movimentacaoEstoque.getMovEstQtd());
        movEstoqueDto.setMovEstQtdAnterior(movimentacaoEstoque.getMovEstQtdAnterior());
        movEstoqueDto.setMovEstQtdPosterior(movimentacaoEstoque.getMovEstQtdPosterior());
        movEstoqueDto.setMovEstTipo(movimentacaoEstoque.getMovEstTipo());
        movEstoqueDto.setMovEstMovimentacao(movimentacaoEstoque.getMovEstMovimentacao());

        return movEstoqueDto;
    }

    public ProdutoDto gerarProdutoDto(Produto produto){
        //Preciso criar a movimentacaoSemProdutoDto e inserir em EstoqueMovimentacao, e após isso, inserir no Produto
        
        
        // List<MovEstoqueDto> movEstoqueDtos = new ArrayList<>();
        // for (MovimentacaoEstoque movimentacaoEstoque : produto.getMovimentacaoEstoque()) {
            
        //     movEstoqueDtos.add(this.gerarMovEstoqueDto(movimentacaoEstoque));
        // }

        //Há vezes em que a categoria e unidade não vem no produto. Desse modo, é preciso busca-las
        //Depois vou reavaliar se realmente essas duas buscas valem a pena ou se obrigo passar os dados completos no produto (atualmente os produtos podem vir sem o nome da unidade e categoria)
        Optional<CategoriaProduto> categoria = this.categoriaProdutoRepository.findById(produto.getProCategoria().getCatProId());
        Optional<UnidadeProduto> unidade = this.unidadeProdutoRepository.findById(produto.getProUn().getUnId());

        ProdutoDto produtoDto = new ProdutoDto(
            produto.getProId(),
            produto.getProNome(),
            produto.getProSipac(),
            unidade.get().getUnSigla(),
            produto.getProUn() != null ? produto.getProUn().getUnId() : null,
            categoria.get().getCatProNome(),
            produto.getProCategoria() != null ? produto.getProCategoria().getCatProId() : null,
            produto.getProQtd(),
            produto.getProEstoqueMin(),
            produto.getIsAbaixoMin(),
            produto.getIsAtivo()
        );
        
        return produtoDto;
    }

    public Produto buscarProdutoPorId(Long id) {
        Produto produto = this.produtoRepository.findById(id).
                orElseThrow(() -> new ErroAoBuscarObjetos("Falha ao buscar produto com id: "+id));
        return produto;
    }

    public List<ProdutoDto> buscarTodosProdutos() {
        List<Produto> produtos = this.produtoRepository.buscarProdutosAtivos();
        
        List<ProdutoDto> produtoDtos = new ArrayList<>();
        for (Produto produto : produtos) {
            produtoDtos.add(gerarProdutoDto(produto));
        }   
        return produtoDtos;
    }

    public ProdutoDto cadastrarProduto(Produto produto) {
        produto.setProId(null);
        Produto ProdutoCadastrado = this.produtoRepository.save(produto);
        return gerarProdutoDto(ProdutoCadastrado);
    }

    public ProdutoDto atualizarProduto(Produto produto) {
        Produto newProduto = this.buscarProdutoPorId(produto.getProId());

        BeanUtils.copyProperties(produto, newProduto, "proId");
        newProduto.setProUn(produto.getProUn());
        newProduto.setProCategoria(produto.getProCategoria());
        this.produtoRepository.save(newProduto);
        return gerarProdutoDto(newProduto);
    }

    public ProdutoDto alterarStatusAtivoProduto(Long id) {
        Produto produto = this.buscarProdutoPorId(id);

        produto.setIsAtivo(!produto.getIsAtivo());
        this.atualizarProduto(produto);
        return(gerarProdutoDto(produto));
    }
}
