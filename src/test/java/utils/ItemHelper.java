package utils;

import java.math.BigDecimal;

import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.item.CategoriaItem;
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

    public static ItemDTO criarItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setNome(item.getNome());
        itemDTO.setPreco(item.getPreco());
        itemDTO.setCategoria(item.getCategoria());

        return itemDTO;
    }

    public static Item criarItem() {
        return criarItem(null, null, null, null);
    }

    public static Item criarItem(Long id, String nome, BigDecimal preco, CategoriaItem categoria) {
        Item item = new Item();
        item.setId(id != null ? id : 1L);
        item.setNome(nome != null && !nome.isEmpty() ? nome : "Hamburger");
        item.setPreco(preco != null ? preco : new BigDecimal(10));
        item.setCategoria(categoria != null ? categoria : CategoriaItem.LANCHE);

        return item;
    }
}
