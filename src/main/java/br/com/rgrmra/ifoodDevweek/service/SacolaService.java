package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Sacola;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ItemDto;

public interface SacolaService {

    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long sacolaId, int formaDePagamento);
}
