package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.enumeration.PaymentMethod;
import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Cart;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ItemDto;
import br.com.rgrmra.ifoodDevweek.service.CartService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/ifood-dev-week/carts")
@RestController
@RequestMapping("/ifood-dev-week/carts")
@RequiredArgsConstructor
public class CartResource {

    private final CartService cartService;

    @GetMapping
    public List<Cart> listCarts() {
        return cartService.listCarts();
    }

    @PostMapping
    public Cart addCart(@RequestParam Long clientId) {
        return cartService.addCart(clientId);
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable("id") Long id) {
        return cartService.getCartById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Cart> getCartsByClientId(@PathVariable("clientId") Long clientId) {
        return cartService.getCartsByClientId(clientId);
    }

    @PatchMapping("{id}/add")
    public Item addItemInCart(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        return cartService.addItemInCart(id, itemDto);
    }

    @DeleteMapping("/{id}/delete/{itemId}")
    public Cart deleteItemInCart(@PathVariable("id") Long cartId, @PathVariable("itemId") Long itemId) {
        return cartService.deleteItemInCart(cartId, itemId);
    }

    @PatchMapping("/{id}/payment-method")
    public Cart setCartPaymentMethod(@PathVariable("id") Long id, @RequestParam("paymentMethod") PaymentMethod paymentMethod) {
        return cartService.setCartPaymentMethod(id, paymentMethod);
    }

    @PatchMapping("/{id}/checkout")
    public Cart checkout(@PathVariable("id") Long id) {
        return cartService.checkout(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
    }
}
