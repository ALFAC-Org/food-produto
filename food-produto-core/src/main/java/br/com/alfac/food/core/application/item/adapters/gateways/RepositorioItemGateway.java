package br.com.alfac.food.core.application.item.adapters.gateways;

import java.util.List;
import java.util.Optional;

import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;

public interface RepositorioItemGateway {

    List<Item> consultarItens();

    List<Item> consultarItensPorCategoria(CategoriaItem categoria);

    Optional<Item> consultarItemPorId(Long id);

    Item cadastrarItem(Item item);

    Item atualizarItem(Long id, ItemDTO item);

    Item excluirItem(Long id);
}
