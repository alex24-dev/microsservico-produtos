package br.com.empresa.produto.controller;

import br.com.empresa.produto.dto.produto.ProdutoDto;
import br.com.empresa.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

   @Autowired
    private ProdutoService service;

//    public ProdutoController(ProdutoService service) {
//        this.service = service;
//    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoDto dto, UriComponentsBuilder uri){

        ProdutoDto produtoDto = service.criarProduto(dto);
        URI endereco = uri.path("/produtos").buildAndExpand().toUri();
        return ResponseEntity.created(endereco).body(produtoDto);
    }

    @GetMapping
   // @Cacheable( value = {"produtos"})
    public ResponseEntity<Page<ProdutoDto>> listar(@PageableDefault(size = 10, sort = {"nome"} )Pageable paginacao){

       // return service.consultar(paginacao);
        return ResponseEntity.ok(service.consultar(paginacao));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> detalhar(@PathVariable  long id){

        ProdutoDto dto = service.obterPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable  Long id, @RequestBody @Valid ProdutoDto dto){

        ProdutoDto atualizado = service.atualizarPagamento(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> remover(@PathVariable @Valid Long id){

        service.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }

}
