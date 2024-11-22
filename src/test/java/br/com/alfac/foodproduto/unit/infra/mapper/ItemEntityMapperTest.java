package br.com.alfac.foodproduto.unit.infra.mapper;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.alfac.foodproduto.infra.mapper.ItemEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import br.com.alfac.foodproduto.infra.persistence.ItemEntity;


class ItemEntityMapperTest {

    private ItemEntityMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(ItemEntityMapper.class);
    }

    @Test
    void testToEntity() {
        Item item = new Item();
        item.setId(1L);
        item.setNome("Hamburguer");
        item.setPreco(new BigDecimal(10.0));
        item.setCategoria(CategoriaItem.LANCHE);

        ItemEntity itemEntity = mapper.toEntity(item);

        assertEquals(item.getId(), itemEntity.getId());
        assertEquals(item.getNome(), itemEntity.getNome());
        assertEquals(item.getPreco(), itemEntity.getPreco());
        assertEquals(item.getCategoria(), itemEntity.getCategoria());
    }

    @Test
    void testToEntity_QuandoItemNulo() {
        Item item = null;

        ItemEntity itemEntity = mapper.toEntity(item);

        assertThat(itemEntity).isNull();
    }

    @Test
    void testToDomain() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(1L);
        itemEntity.setNome("Hamburguer");
        itemEntity.setPreco(new BigDecimal(10.0));
        itemEntity.setCategoria(CategoriaItem.LANCHE);

        Item item = mapper.toDomain(itemEntity);

        assertEquals(itemEntity.getId(), item.getId());
        assertEquals(itemEntity.getNome(), item.getNome());
        assertEquals(itemEntity.getPreco().setScale(2), item.getPreco());
        assertEquals(itemEntity.getCategoria(), item.getCategoria());
    }

    @Test
    void testToDomain_QuandoItemEntityNulo() {
        ItemEntity itemEntity = null;

        Item item = mapper.toDomain(itemEntity);

        assertThat(item).isNull();
    }

}