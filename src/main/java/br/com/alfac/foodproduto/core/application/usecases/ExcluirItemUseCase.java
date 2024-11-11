package br.com.alfac.foodproduto.core.application.usecases;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;

public class ExcluirItemUseCase {

    private final RepositorioItemGateway itemRepository;

    public ExcluirItemUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item execute(Long id) throws FoodProdutoException {
        Item item = itemRepository.excluirItem(id);
        return item;
    }

}
