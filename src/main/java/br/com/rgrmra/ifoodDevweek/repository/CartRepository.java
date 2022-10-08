package br.com.rgrmra.ifoodDevweek.repository;

import br.com.rgrmra.ifoodDevweek.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
