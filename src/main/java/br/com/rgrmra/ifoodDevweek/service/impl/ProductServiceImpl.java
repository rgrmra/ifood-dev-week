package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Product;
import br.com.rgrmra.ifoodDevweek.model.Restaurant;
import br.com.rgrmra.ifoodDevweek.repository.ProdutRepository;
import br.com.rgrmra.ifoodDevweek.repository.RestaurantRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProductDto;
import br.com.rgrmra.ifoodDevweek.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProdutRepository productRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        return updateRestaurantList(productRepository.save(Product.builder()
                .name(productDto.getName())
                .price(new BigDecimal(String.valueOf(productDto.getPrice())))
                .available(productDto.isAvailable())
                .restaurant(restaurantRepository.getReferenceById(productDto.getRestaurantId()))
                .build()));
    }

    private Product updateRestaurantList(Product newProduct) {
        Restaurant restaurant = restaurantRepository.getReferenceById(newProduct.getRestaurant().getId());
        List<Product> productsList = restaurantRepository.getReferenceById(restaurant.getId()).getProducts();
        productsList.add(newProduct);
        restaurant.setProducts(productsList);
        restaurantRepository.save(restaurant);
        return newProduct;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Product doesn't exist!");
                }
        );
    }

    @Override
    public List<Product> searchProductByName(String name) {
        List<Product> productsList = productRepository.findAll();
        productsList.removeIf(product -> !product.getName().toUpperCase().contains(name.toUpperCase()));
        return productsList;
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = getProductById(id);
        if (!productDto.getName().isEmpty())
            product.setName(productDto.getName());
        if (!(productDto.getPrice().equals(new BigDecimal("0.0"))))
            product.setPrice(productDto.getPrice());
        if (productDto.isAvailable() != product.isAvailable())
            product.setAvailable(productDto.isAvailable());
        if(!(productDto.getRestaurantId() == 0))
            product.setRestaurant(restaurantRepository.getReferenceById(productDto.getRestaurantId()));
        return updateRestaurantList(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        Restaurant restaurant = restaurantRepository.getReferenceById(getProductById(productId).getRestaurant().getId());
        restaurant.getProducts().removeIf(product -> (product.getId() == productId));
        restaurantRepository.save(restaurant);
        productRepository.deleteById(productId);
    }
}
