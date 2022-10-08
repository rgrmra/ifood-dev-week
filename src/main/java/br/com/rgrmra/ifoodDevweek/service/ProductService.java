package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Product;
import br.com.rgrmra.ifoodDevweek.model.Restaurant;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> listProducts();
    Product addProduct(ProductDto productDto);
    Product getProductById(Long id);
    String getProductNameById(Long id);
    BigDecimal getProductPriceById(Long id);
    boolean getProductAvailabilityById(Long id);
    Restaurant getProductRestaurantById(Long id);
    List<Product> searchProductByName(String nome);
    Product updateProduct(Long id, ProductDto productDto);
    Product updateProductName(Long id, String nome);
    Product updateProductPrice(Long id, BigDecimal valorUnitario);
    Product updateProductAvailability(Long id, boolean disponivel);
    Product updateProductRestaurant(Long id, Long restauranteId);
    void deleteProduct(Long id);
}
