package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.enumeration.PaymentMethod;
import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Cart;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ItemDto;

import java.util.List;

public interface CartService {

    List<Cart> listCarts();
    Cart addCart(Long clienteId);
    Cart getCartById(Long id);
    List<Cart> getCartsByClientId(Long clienteId);
    Item addItemInCart(Long id, ItemDto itemDto);
    Cart deleteItemInCart(Long sacolaId, Long itemId);
    Cart setCartPaymentMethod(Long id, PaymentMethod paymentMethod);
    Cart checkout(Long id);
    void deleteCart(Long id);
}
