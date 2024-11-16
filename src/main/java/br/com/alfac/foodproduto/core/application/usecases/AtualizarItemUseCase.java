package br.com.alfac.foodproduto.core.application.usecases;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;

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
