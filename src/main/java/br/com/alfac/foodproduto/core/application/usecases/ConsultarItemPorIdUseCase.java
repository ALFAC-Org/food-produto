package br.com.alfac.foodproduto.core.application.usecases;

import java.util.Optional;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import br.com.alfac.foodproduto.core.exception.item.ItemError;

public class ConsultarItemPorIdUseCase {

    private final RepositorioItemGateway itemRepository;

    public ConsultarItemPorIdUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item execute(Long id) throws FoodProdutoException {
        Optional<Item> itemOpt = itemRepository.consultarItemPorId(id);
        return itemOpt.orElseThrow(() -> new FoodProdutoException(ItemError.ITEM_NAO_ENCONTRADO));
    }

}