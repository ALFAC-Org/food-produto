package br.com.alfac.foodproduto.core.application.adapters.presenter;

import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.Item;

import java.util.ArrayList;
import java.util.List;

public final class ItemPresenter {

    private ItemPresenter() {
    }

    public static ItemDTO mapearParaItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCategoria(item.getCategoria());
        itemDTO.setId(item.getId());
        itemDTO.setNome(item.getNome());
        itemDTO.setPreco(item.getPreco());

        return itemDTO;
    }

    public static List<ItemDTO> mapearParaItemDTOList(List<Item> itemList) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        
        if (itemList == null) {
            return itemDTOList;
        }

        for (Item item : itemList) {
            itemDTOList.add(mapearParaItemDTO(item));
        }

        return itemDTOList;
    }

}
