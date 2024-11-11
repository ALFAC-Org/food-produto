package utils;

import java.math.BigDecimal;

import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.item.Item;

public abstract class ItemHelper {

    public static ItemDTO criarItemDTO() {
        return criarItemDTO(null, null);
    }

    public static ItemDTO criarItemDTO(String nome, BigDecimal preco) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setNome(nome != null && !nome.isEmpty() ? nome : "Hamburger");
        itemDTO.setPreco(preco != null ? preco : new BigDecimal(10));

        return itemDTO;
    }

    public static Item criarItem() {
        return criarItem(null, null);
    }

    public static Item criarItem(String nome, BigDecimal preco) {
        Item item = new Item();
        long id = 1;

        item.setId(id);
        item.setNome(nome != null && !nome.isEmpty() ? nome : "Hamburger");
        item.setPreco(preco != null ? preco : new BigDecimal(10));

        return item;
    }
}
