package br.com.alfac.foodproduto.infra.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import br.com.alfac.foodproduto.infra.dto.ItemRequest;


public class ItemMapperTest {

    private ItemMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(ItemMapper.class);
    }

    @Test
    public void testToDTO() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setNome("Hamburguer");
        itemRequest.setPreco(10.0);
        itemRequest.setCategoria(CategoriaItem.LANCHE);

        ItemDTO itemDTO = mapper.toDTO(itemRequest);

        assertEquals(itemRequest.getNome(), itemDTO.getNome());
        assertEquals(itemRequest.getPreco(), itemDTO.getPreco().doubleValue());
        assertEquals(itemRequest.getCategoria(), itemDTO.getCategoria());
    }

    @Test
    public void testToEntity_QuandoItemNulo() {
        ItemRequest itemRequest = null;

        ItemDTO itemDTO = mapper.toDTO(itemRequest);

        assertThat(itemDTO).isNull();
    }

}