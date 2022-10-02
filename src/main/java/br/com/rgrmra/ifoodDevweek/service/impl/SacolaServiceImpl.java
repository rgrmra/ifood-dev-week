package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.enumeration.FormaPagamento;
import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.model.Sacola;
import br.com.rgrmra.ifoodDevweek.repository.ItemRepository;
import br.com.rgrmra.ifoodDevweek.repository.ProdutoRepository;
import br.com.rgrmra.ifoodDevweek.repository.SacolaRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ItemDto;
import br.com.rgrmra.ifoodDevweek.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());

        if (sacola.isFechada()) {
            throw new RuntimeException("Esta sacola está fechada!");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Esse produto não existe!");
                        }
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
                itensDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes!" +
                        "Feche a sacola ou esvazie!");
            }
        }

        List<Double> valorDosItens = new ArrayList<>();

        for (Item itemDaSacola : itensDaSacola) {
            double valorTotalItem = itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        Double valorTotalSacola = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();

        sacola.setValorTotal(valorTotalSacola);

        sacolaRepository.save(sacola);

        return itemParaSerInserido;
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Essa sacola não exite!");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long sacolaId, int numeroFormaDePagamento) {
        Sacola sacola = verSacola(sacolaId);
        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Sacola vazia! Inclua itens na sacola!");
        }

        FormaPagamento formaPagamento =
                numeroFormaDePagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);

        return sacolaRepository.save(sacola);
    }
}
