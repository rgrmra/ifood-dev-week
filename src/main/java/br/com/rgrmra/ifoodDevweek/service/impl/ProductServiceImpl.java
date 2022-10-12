package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.exception.NullPriceException;
import br.com.rgrmra.ifoodDevweek.exception.ProductNotFoundException;
import br.com.rgrmra.ifoodDevweek.exception.RestaurantNotFoundException;
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

        if (productDto.getPrice().signum() != 1) {
            throw new NullPriceException();
        }

        return updateRestaurantList(productRepository.save(Product.builder()
                .name(productDto.getName())
                .price(new BigDecimal(String.valueOf(productDto.getPrice())))
                .available(productDto.isAvailable())
                .restaurant(getRestaurantById(productDto.getRestaurantId()))
                .build()));
    }

    private Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(
                () -> {
                    throw new RestaurantNotFoundException(restaurantId);
                }
        );
    }

    private Product updateRestaurantList(Product newProduct) {
        Restaurant restaurant = getRestaurantById(newProduct.getRestaurant().getId());
        List<Product> productsList = restaurant.getProducts();
        productsList.add(newProduct);
        restaurant.setProducts(productsList);
        restaurantRepository.save(restaurant);
        return newProduct;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> {
                    throw new ProductNotFoundException(productId);
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
    public Product updateProduct(Long productId, ProductDto productDto) {
        Product product = getProductById(productId);
        if (!productDto.getName().isEmpty())
            product.setName(productDto.getName());
        if (!(productDto.getPrice().equals(new BigDecimal("0.0"))))
            product.setPrice(productDto.getPrice());
        if (productDto.isAvailable() != product.isAvailable())
            product.setAvailable(productDto.isAvailable());
        if(!(productDto.getRestaurantId() == 0)) {
            Restaurant restaurant = getRestaurantById(getProductById(productId).getRestaurant().getId());
            restaurant.getProducts().removeIf(productRestaurant -> (productRestaurant.getId() == productId));
            restaurantRepository.save(restaurant);
            product.setRestaurant(getRestaurantById(productDto.getRestaurantId()));
        }
        return updateRestaurantList(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        Restaurant restaurant = getRestaurantById(getProductById(productId).getRestaurant().getId());
        restaurant.getProducts().removeIf(product -> (product.getId() == productId));
        restaurantRepository.save(restaurant);
        productRepository.deleteById(productId);
    }
}
