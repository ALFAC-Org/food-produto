package br.com.alfac.food.core.application.item.adapters.controller;

import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.food.core.application.item.adapters.presenter.ItemPresenter;
import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.item.usecases.AtualizarItemUseCase;
import br.com.alfac.food.core.application.item.usecases.CadastrarItemUseCase;
import br.com.alfac.food.core.application.item.usecases.ConsultarItemPorIdUseCase;
import br.com.alfac.food.core.application.item.usecases.ConsultarItensPorCategoriaUseCase;
import br.com.alfac.food.core.application.item.usecases.ConsultarItensUseCase;
import br.com.alfac.food.core.application.item.usecases.ExcluirItemUseCase;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodProdutoException;

import java.util.List;

public class ControladorItem {

    private final RepositorioItemGateway repositorioItemGateway;

    public ControladorItem(final RepositorioItemGateway repositorioItemGateway) {
        this.repositorioItemGateway = repositorioItemGateway;
    }

    public List<ItemDTO> consultarItens() throws FoodProdutoException {
        ConsultarItensUseCase consultarItensUseCase = new ConsultarItensUseCase(this.repositorioItemGateway);
        List<Item> itemList = consultarItensUseCase.execute();
        return ItemPresenter.mapearParaItemDTOList(itemList);
    }

    public List<ItemDTO> consultarItensPorCategoria(CategoriaItem categoria) throws FoodProdutoException {
        ConsultarItensPorCategoriaUseCase consultarItensPorCategoriaUseCase = new ConsultarItensPorCategoriaUseCase(this.repositorioItemGateway);
        List<Item> itemList = consultarItensPorCategoriaUseCase.execute(categoria);
        return ItemPresenter.mapearParaItemDTOList(itemList);
    }

    public ItemDTO consultarItemPorId(Long id) throws FoodProdutoException {
        ConsultarItemPorIdUseCase consultarItemPorIdUseCase = new ConsultarItemPorIdUseCase(this.repositorioItemGateway);
        Item item = consultarItemPorIdUseCase.executar(id);
        return ItemPresenter.mapearParaItemDTO(item);
    }

    public ItemDTO cadastrarItem(ItemDTO itemDTO) throws FoodProdutoException {
        CadastrarItemUseCase cadastrarItemUseCase = new CadastrarItemUseCase(this.repositorioItemGateway);
        Item item = cadastrarItemUseCase.execute(itemDTO);
        return ItemPresenter.mapearParaItemDTO(item);
    }

    public ItemDTO atualizarItem(Long id, ItemDTO itemDTO) throws FoodProdutoException {
        AtualizarItemUseCase atualizarItemUseCase = new AtualizarItemUseCase(this.repositorioItemGateway);
        Item item = atualizarItemUseCase.execute(id, itemDTO);
        return ItemPresenter.mapearParaItemDTO(item);
    }

    public ItemDTO excluirItem(Long id) throws FoodProdutoException {
        ExcluirItemUseCase excluirItemUseCase = new ExcluirItemUseCase(this.repositorioItemGateway);
        Item item = excluirItemUseCase.execute(id);
        return ItemPresenter.mapearParaItemDTO(item);
    }

}
