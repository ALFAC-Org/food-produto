package br.com.alfac.foodproduto.core.application.adapters.controller;

import java.util.List;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.application.adapters.presenter.ItemPresenter;
import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.application.usecases.AtualizarItemUseCase;
import br.com.alfac.foodproduto.core.application.usecases.CadastrarItemUseCase;
import br.com.alfac.foodproduto.core.application.usecases.ConsultarItemPorIdUseCase;
import br.com.alfac.foodproduto.core.application.usecases.ConsultarItensPorCategoriaUseCase;
import br.com.alfac.foodproduto.core.application.usecases.ConsultarItensUseCase;
import br.com.alfac.foodproduto.core.application.usecases.ExcluirItemUseCase;
import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;

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
        Item item = consultarItemPorIdUseCase.execute(id);
        return ItemPresenter.mapearParaItemDTO(item);
    }

    public ItemDTO cadastrarItem(ItemDTO itemDTO) throws FoodProdutoException {
        List<ItemDTO> itensExistentes = consultarItens();
        long newId = itensExistentes.stream().mapToLong(ItemDTO::getId).max().orElse(0) + 1;
        itemDTO.setId(newId);

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
