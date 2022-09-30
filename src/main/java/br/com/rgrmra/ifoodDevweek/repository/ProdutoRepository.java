package br.com.rgrmra.ifoodDevweek.repository;

import br.com.rgrmra.ifoodDevweek.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
