package br.com.alfac.foodproduto.core.application.adapters.gateways;

import java.util.List;
import java.util.Optional;

import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.item.CategoriaItem;
import br.com.alfac.foodproduto.core.domain.item.Item;

public interface RepositorioItemGateway {

    List<Item> consultarItens();

    List<Item> consultarItensPorCategoria(CategoriaItem categoria);

    Optional<Item> consultarItemPorId(Long id);

    Item cadastrarItem(Item item);

    Item atualizarItem(Long id, ItemDTO item);

    Item excluirItem(Long id);
}
