package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.enumeration.FormaPagamento;
import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.model.Sacola;
import br.com.rgrmra.ifoodDevweek.repository.ClienteRepository;
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
    private final ClienteRepository clienteRepository;

    @Override
    public List<Sacola> listarSacolas() {
        return sacolaRepository.findAll();
    }

    @Override
    public Sacola adicionarSacola(Long clienteId) {
        return sacolaRepository.save(Sacola.builder()
                .cliente(clienteRepository.findById(clienteId).orElseThrow(
                        () -> {
                            throw new RuntimeException("Cliente não existe!");
                        }
                )).build());
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
    public List<Sacola> verSacolaPeloCliente(Long clienteId) {
        List<Sacola> listaSacolas = sacolaRepository.findAll();
        listaSacolas.removeIf(sacola -> (sacola.getCliente().getId() != clienteId));
        return listaSacolas;
    }

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());
        verificaSacolaFechada(sacola);

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

        sacola.setValorTotal(atualizarValorDaSacola(itensDaSacola));
        sacolaRepository.save(sacola);

        return itemParaSerInserido;
    }

    private double atualizarValorDaSacola(List<Item> itensDaSacola) {
        List<Double> valorDosItens = new ArrayList<>();

        for (Item itemDaSacola : itensDaSacola) {
            double valorTotalItem = itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        return valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();
    }

    @Override
    public Sacola removerItemNaSacola(Long sacolaId, Long itemId) {
        Sacola sacola = verSacola(sacolaId);
        verificaSacolaFechada(sacola);
        List<Item> listaItems = sacola.getItens();
        listaItems.removeIf(item -> (item.getId() == itemId));
        sacola.setItens(listaItems);
        sacola.setValorTotal(atualizarValorDaSacola(listaItems));
        return sacolaRepository.save(sacola);
    }

    @Override
    public Sacola formaPagamentoSacola(Long id, FormaPagamento formaPagamento) {
        Sacola sacola = verSacola(id);
        verificaSacolaFechada(sacola);
        sacola.setFormaPagamento(formaPagamento);
        return sacolaRepository.save(sacola);
    }

    private void verificaSacolaFechada(Sacola sacola) {
        if (sacola.isFechada()) {
            throw new RuntimeException("Impossível! A sacola já está fechada!");
        }
    }

    @Override
    public Sacola fecharSacola(Long sacolaId) {
        Sacola sacola = verSacola(sacolaId);
        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Sacola vazia! Inclua itens na sacola!");
        }

        if (sacola.getFormaPagamento() == null) {
            throw new RuntimeException("Defina uma forma de pagamento antes de fechar a sacola");
        }

        sacola.setFechada(true);

        return sacolaRepository.save(sacola);
    }

    @Override
    public void deletarSacola(Long id) {
        Sacola sacola = verSacola(id);
        sacolaRepository.delete(sacola);
    }
}
