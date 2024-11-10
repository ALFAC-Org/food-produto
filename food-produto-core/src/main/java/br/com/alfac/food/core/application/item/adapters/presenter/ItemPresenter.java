package br.com.alfac.food.core.application.item.adapters.presenter;

import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.domain.item.Item;

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

    public static Item mapearParaItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setCategoria(itemDTO.getCategoria());
        item.setId(itemDTO.getId());
        item.setNome(itemDTO.getNome());
        item.setPreco(itemDTO.getPreco());

        return item;
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
