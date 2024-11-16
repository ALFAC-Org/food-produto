package br.com.alfac.foodproduto.core.application.usecases;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;

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
