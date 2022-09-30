package br.com.rgrmra.ifoodDevweek.repository;

import br.com.rgrmra.ifoodDevweek.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
