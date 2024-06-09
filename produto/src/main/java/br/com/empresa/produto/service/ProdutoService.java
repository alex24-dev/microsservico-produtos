package br.com.empresa.produto.service;


import br.com.empresa.produto.domain.produto.Produto;
import br.com.empresa.produto.dto.produto.ProdutoDto;
import br.com.empresa.produto.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDto criarProduto(ProdutoDto dto) {

        Produto produto = modelMapper.map(dto, Produto.class);
        repository.save(produto);
        log.info("" + produto);
        return modelMapper.map(produto, ProdutoDto.class);
    }

    public Page<ProdutoDto> consultar(Pageable paginacao) {
        return repository.findAll(paginacao).map(p -> modelMapper.map(p, ProdutoDto.class));
    }

    public ProdutoDto obterPorId(Long id){

        Produto produto = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(produto,ProdutoDto.class);
    }

    public ProdutoDto atualizarPagamento(Long id, ProdutoDto dto){

        Produto produto = modelMapper.map(dto, Produto.class);
        produto.setId(id);
        produto = repository.save(produto);
        return modelMapper.map(produto,ProdutoDto.class);
    }

    public void excluirPagamento(Long id){
        repository.deleteById(id);
    }
}
