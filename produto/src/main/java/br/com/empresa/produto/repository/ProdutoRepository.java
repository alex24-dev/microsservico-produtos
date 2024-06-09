package br.com.empresa.produto.repository;


import br.com.empresa.produto.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto,Long> {
}
