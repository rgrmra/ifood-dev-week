package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.enumeration.FormaPagamento;
import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Sacola;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ItemDto;

import java.util.List;

public interface SacolaService {

    List<Sacola> listarSacolas();
    Sacola adicionarSacola(Long clienteId);
    Sacola verSacola(Long id);
    List<Sacola> verSacolaPeloCliente(Long clienteId);
    Item incluirItemNaSacola(Long id, ItemDto itemDto);
    Sacola removerItemNaSacola(Long sacolaId, Long itemId);
    Sacola formaPagamentoSacola(Long id, FormaPagamento formaPagamento);
    Sacola fecharSacola(Long id);
    void deletarSacola(Long id);
}
