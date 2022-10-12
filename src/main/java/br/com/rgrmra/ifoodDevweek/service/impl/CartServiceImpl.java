package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.enumeration.PaymentMethod;
import br.com.rgrmra.ifoodDevweek.exception.*;
import br.com.rgrmra.ifoodDevweek.model.*;
import br.com.rgrmra.ifoodDevweek.repository.ClientRepository;
import br.com.rgrmra.ifoodDevweek.repository.ProdutRepository;
import br.com.rgrmra.ifoodDevweek.repository.CartRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ItemDto;
import br.com.rgrmra.ifoodDevweek.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProdutRepository productRepository;
    private final ClientRepository clientRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addCart(Long clientId) {
        return cartRepository.save(Cart.builder()
                .client(clientRepository.findById(clientId).orElseThrow(
                        () -> {
                            throw new ClientNotFoundException(clientId);
                        }
                ))
                .build());
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(
                () -> {
                    throw new CartNotFoundException(cartId);
                }
        );
    }

    @Override
    public List<Cart> getCartsByClientId(Long clientId) {
        List<Cart> listCarts = cartRepository.findAll();
        listCarts.removeIf(cart -> (cart.getClient().getId() != clientId));
        return listCarts;
    }

    @Override
    public Item addItemInCart(Long cartId, ItemDto itemDto) {
        Cart cart = getCartById(cartId);
        checkIfCartIsClosed(cart);

        Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(
                () -> {
                    throw new ProductNotFoundException(itemDto.getProductId());
                }
        );

        if (!product.isAvailable())
            throw new ProductNotAvailableException(cartId, product.getId());

        Item newItem = Item.builder()
                .quantity(itemDto.getQuantity())
                .cart(cart)
                .itemProduct(ItemProduct.builder()
                        .nome(product.getName())
                        .price(new BigDecimal(String.valueOf(product.getPrice())))
                        .restaurantId(product.getRestaurant().getId())
                        .build())
                .build();

        List<Item> cartItems = cart.getItens();
        if (cartItems.isEmpty()) {
            cartItems.add(newItem);
        } else {
            Long cartRestaurantId = cartItems.get(0).getItemProduct().getRestaurantId();
            Long itemRestaurantId = newItem.getItemProduct().getRestaurantId();
            if (cartRestaurantId.equals(itemRestaurantId)) {
                cartItems.add(newItem);
            } else {
                throw new DifferentRestaurantException(cartId, cartRestaurantId, itemRestaurantId);
            }
        }

        cart.setFinalPrice(updateCartPrice(cartItems));
        cartRepository.save(cart);

        return newItem;
    }

    private BigDecimal updateCartPrice(List<Item> cartItems) {
        BigDecimal itemsFinalPrice = new BigDecimal("0.0");
        for (Item cartItem : cartItems) {
            itemsFinalPrice = itemsFinalPrice.add(
                    cartItem.getItemProduct().getPrice().multiply(
                            new BigDecimal(cartItem.getQuantity())
                    )
            );
        }
        return itemsFinalPrice;
    }

    @Override
    public Cart deleteItemInCart(Long cartId, Long itemId) {
        Cart cart = getCartById(cartId);
        checkIfCartIsClosed(cart);
        List<Item> itemsList = cart.getItens();
        itemsList.removeIf(item -> (item.getId() == itemId));
        cart.setItens(itemsList);
        cart.setFinalPrice(updateCartPrice(itemsList));
        return cartRepository.save(cart);
    }

    @Override
    public Cart setCartPaymentMethod(Long id, PaymentMethod paymentMethod) {
        Cart cart = getCartById(id);
        checkIfCartIsClosed(cart);
        cart.setPaymentMethod(paymentMethod);
        return cartRepository.save(cart);
    }

    private void checkIfCartIsClosed(Cart cart) {
        if (cart.isClosed()) {
            throw new ClosedCartException(cart.getId());
        }
    }

    @Override
    public Cart checkout(Long cartId) {
        Cart cart = getCartById(cartId);
        if (cart.getItens().isEmpty()) {
            throw new EmptyCartException(cartId);
        }

        if (cart.getPaymentMethod() == null) {
            throw new PaymentMethodNotDefinedException(cartId);
        }

        cart.setClosed(true);

        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.delete(getCartById(id));
    }
}
