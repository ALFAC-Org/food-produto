package br.com.alfac.food.core.application.item.usecases;

import java.util.Optional;

import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodProdutoException;
import br.com.alfac.food.core.exception.item.ItemError;

public class ConsultarItemPorIdUseCase {

    private final RepositorioItemGateway itemRepository;

    public ConsultarItemPorIdUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item executar(Long id) throws FoodProdutoException {
        Optional<Item> itemOpt = itemRepository.consultarItemPorId(id);
        return itemOpt.orElseThrow(() -> new FoodProdutoException(ItemError.ITEM_NAO_ENCONTRADO));
    }

}