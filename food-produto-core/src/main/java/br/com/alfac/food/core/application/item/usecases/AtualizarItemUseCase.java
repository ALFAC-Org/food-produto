package br.com.alfac.food.core.application.item.usecases;

import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodProdutoException;

public class AtualizarItemUseCase {

    private final RepositorioItemGateway itemRepository;

    public AtualizarItemUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item execute(Long id, ItemDTO item) throws FoodProdutoException {
        Item itemAtualizado = itemRepository.atualizarItem(id, item);

        return itemAtualizado;
    }

}
