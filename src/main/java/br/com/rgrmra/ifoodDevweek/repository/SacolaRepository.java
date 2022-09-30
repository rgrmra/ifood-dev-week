package br.com.rgrmra.ifoodDevweek.repository;

import br.com.rgrmra.ifoodDevweek.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {
}
