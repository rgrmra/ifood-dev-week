package br.com.rgrmra.ifoodDevweek.repository;

import br.com.rgrmra.ifoodDevweek.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
