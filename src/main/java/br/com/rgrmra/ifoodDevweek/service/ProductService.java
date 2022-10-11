package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Product;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product addProduct(ProductDto productDto);
    Product getProductById(Long id);
    List<Product> searchProductByName(String nome);
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
