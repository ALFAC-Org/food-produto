package utils;

import java.math.BigDecimal;
import java.util.Random;

import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.item.CategoriaItem;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.infra.dto.ItemRequest;
import br.com.alfac.foodproduto.infra.persistence.ItemEntity;

public abstract class ItemHelper {

    public static ItemRequest criarItemRequest() {
        ItemRequest itemRequest = new ItemRequest();

        itemRequest.setNome("Hamburger");
        itemRequest.setCategoria(CategoriaItem.LANCHE);
        itemRequest.setPreco(10);

        return itemRequest;
    }

    public static ItemDTO criarItemDTO() {
        return criarItemDTO(null, null, null, null);
    }

    public static ItemDTO criarItemDTO(Long id, String nome, BigDecimal preco, CategoriaItem categoria) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(id != null ? id : randomId());
        itemDTO.setNome(nome != null && !nome.isEmpty() ? nome : "Hamburger");
        itemDTO.setCategoria(categoria != null ? categoria : CategoriaItem.LANCHE);
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

    public static ItemDTO criarItemDTO(ItemRequest itemRequest) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setNome(itemRequest.getNome());
        itemDTO.setPreco(new BigDecimal(itemRequest.getPreco()));
        itemDTO.setCategoria(itemRequest.getCategoria());

        return itemDTO;
    }

    public static Item criarItem() {
        return criarItem(null, null, null, null);
    }

    public static Item criarItem(Long id, String nome, BigDecimal preco, CategoriaItem categoria) {
        Item item = new Item();
        item.setId(id != null ? id : randomId());
        item.setNome(nome != null && !nome.isEmpty() ? nome : "Hamburger");
        item.setPreco(preco != null ? preco : new BigDecimal(10));
        item.setCategoria(categoria != null ? categoria : CategoriaItem.LANCHE);

        return item;
    }

    public static ItemEntity criarItemEntity() {
        return criarItemEntity(null, null, null);
    }

    public static ItemEntity criarItemEntity(String nome, BigDecimal preco, CategoriaItem categoria) {
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setNome(nome != null && !nome.isEmpty() ? nome : "Hamburger");
        itemEntity.setCategoria(categoria != null ? categoria : CategoriaItem.LANCHE);
        itemEntity.setPreco(preco != null ? preco : new BigDecimal(10));

        return itemEntity;
    }

    public static ItemEntity criarItemEntity(Item item) {
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setId(item.getId());
        itemEntity.setNome(item.getNome());
        itemEntity.setPreco(item.getPreco());
        itemEntity.setCategoria(item.getCategoria());

        return itemEntity;
    }

    public static Long randomId(){
        return (long) new Random().nextInt(100000) + 1;
    }

}
