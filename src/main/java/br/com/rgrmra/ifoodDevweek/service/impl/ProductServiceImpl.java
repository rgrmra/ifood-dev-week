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

    private final ProdutRepository produtRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Product> listProducts() {
        return produtRepository.findAll();
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        return updateRestaurantList(produtRepository.save(Product.builder()
                .name(productDto.getName())
                .price(new BigDecimal(String.valueOf(productDto.getPrice())))
                .available(productDto.isAvailable())
                .restaurant(findRestaurant(productDto.getRestaurantId()))
                .build()));
    }

    private Product updateRestaurantList(Product newProduct) {
        Restaurant restaurant = findRestaurant(newProduct.getRestaurant().getId());
        List<Product> productsList = produtRepository.findAll();
        productsList.removeIf(product -> !product.getRestaurant().equals(restaurant));
        restaurant.setProducts(productsList);
        restaurantRepository.save(restaurant);
        return newProduct;
    }

    private Restaurant findRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Restaurant doesn't exist!");
                }
        );
    }

    @Override
    public Product getProductById(Long id) {
        return produtRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Product doesn't exist!");
                }
        );
    }

    @Override
    public String getProductNameById(Long id) {
        return getProductById(id).getName();
    }

    @Override
    public BigDecimal getProductPriceById(Long id) {
        return getProductById(id).getPrice();
    }

    @Override
    public boolean getProductAvailabilityById(Long id) {
        return getProductById(id).isAvailable();
    }

    @Override
    public Restaurant getProductRestaurantById(Long id) {
        return getProductById(id).getRestaurant();
    }

    @Override
    public List<Product> searchProductByName(String name) {
        List<Product> productsList = produtRepository.findAll();
        productsList.removeIf(product -> !product.getName().toUpperCase().contains(name.toUpperCase()));
        return productsList;
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = getProductById(id);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setAvailable(productDto.isAvailable());
        product.setRestaurant(findRestaurant(productDto.getRestaurantId()));
        return updateRestaurantList(produtRepository.save(product));
    }

    @Override
    public Product updateProductName(Long id, String name) {
        Product product = getProductById(id);
        product.setName(name);
        return updateRestaurantList(produtRepository.save(product));
    }

    @Override
    public Product updateProductPrice(Long id, BigDecimal price) {
        Product product = getProductById(id);
        product.setPrice(price);
        return updateRestaurantList(produtRepository.save(product));
    }

    @Override
    public Product updateProductAvailability(Long id, boolean available) {
        Product product = getProductById(id);
        product.setAvailable(available);
        return updateRestaurantList(produtRepository.save(product));
    }

    @Override
    public Product updateProductRestaurant(Long id, Long restaurantId) {
        Product productNovo = getProductById(id);
        Restaurant restaurant = findRestaurant(restaurantId);
        restaurant.getProducts().removeIf(product -> product.equals(getProductById(id)));
        restaurantRepository.save(restaurant);
        return updateRestaurantList(produtRepository.save(productNovo));
    }

    @Override
    public void deleteProduct(Long id) {
        Restaurant restaurant = findRestaurant(getProductById(id).getRestaurant().getId());
        restaurant.getProducts().removeIf(product -> (product.getId() == id));
        restaurantRepository.save(restaurant);
        produtRepository.deleteById(id);
    }
}
