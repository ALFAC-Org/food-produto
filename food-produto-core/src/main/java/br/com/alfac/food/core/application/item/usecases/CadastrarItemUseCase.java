package br.com.alfac.food.core.application.item.usecases;

import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodProdutoException;

public class CadastrarItemUseCase {

    private final RepositorioItemGateway itemRepository;

    public CadastrarItemUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item execute(ItemDTO itemDTO) throws FoodProdutoException {
        Item item = new Item();

        item.setNome(itemDTO.getNome());
        item.setPreco(itemDTO.getPreco());
        item.setCategoria(itemDTO.getCategoria());

        Item itemCriado = itemRepository.cadastrarItem(item);

        return itemCriado;
    }

}
