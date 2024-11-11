package br.com.alfac.foodproduto.core.application.usecases;

import java.util.List;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;

public class ConsultarItensUseCase {

    private final RepositorioItemGateway itemRepository;

    public ConsultarItensUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> execute() throws FoodProdutoException {
        List<Item> itemList = itemRepository.consultarItens();
        return itemList;
    }

}
