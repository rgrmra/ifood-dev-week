package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.model.Product;
import br.com.rgrmra.ifoodDevweek.model.Restaurant;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProdutDto;
import br.com.rgrmra.ifoodDevweek.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/ifood-dev-week/products")
@RestController
@RequestMapping("/ifood-dev-week/products")
@RequiredArgsConstructor
public class ProductResource {
    private final ProductService productService;

    @GetMapping
    public List<Product> listProducts() {
        return productService.listProducts();
    }

    @PostMapping()
    public Product addProduct(@RequestBody ProdutDto produtDto) {
        return productService.addProduct(produtDto);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/name")
    public String getProductNameById(@PathVariable("id") Long id) {
        return productService.getProductNameById(id);
    }

    @GetMapping("/{id}/price")
    public double getProductPriceById(@PathVariable("id") Long id) {
        return productService.getProductPriceById(id);
    }

    @GetMapping("/{id}/availability")
    public boolean getProductAvailabilityById(@PathVariable("id") Long id) {
        return productService.getProductAvailabilityById(id);
    }

    @GetMapping("/{id}/restaurant")
    public Restaurant getProductRestaurantById(@PathVariable("id") Long id) {
        return productService.getProductRestaurantById(id);
    }

    @GetMapping("/search/{name}")
    public List<Product> searchProducts(@PathVariable("name") String nome) {
        return productService.searchProductByName(nome);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProdutDto produtDto) {
        return productService.updateProduct(id, produtDto);
    }

    @PatchMapping("/{id}/name")
    public Product updateProductName(@PathVariable("id") Long id, @RequestParam String name) {
        return productService.updateProductName(id, name);
    }

    @PatchMapping("/{id}/price")
    public Product updateProductPrice(@PathVariable("id") Long id, @RequestParam double price) {
        return productService.updateProductPrice(id, price);
    }

    @PatchMapping("/{id}/availability")
    public Product updateProductAvailability(@PathVariable("id") Long id, @RequestParam boolean available) {
        return productService.updateProductAvailability(id, available);
    }

    @PatchMapping("/{id}/restaurant")
    public Product updateProductRestaurant(@PathVariable("id") Long id, @RequestParam Long restaurantId) {
        return productService.updateProductRestaurant(id, restaurantId);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
