package br.com.alfac.foodproduto.unit.core.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import org.junit.jupiter.api.Test;

import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import utils.ItemHelper;

class ItemDTOTest {

    @Test
    void testEquals() {
        // Arrange
        var id = 1L;

        ItemDTO item1 = ItemHelper.criarItemDTO();
        item1.setId(id);

        ItemDTO item2 = ItemHelper.criarItemDTO();
        item2.setId(id);

        ItemDTO item3 = ItemHelper.criarItemDTO();
        item3.setId(2L);

        ItemDTO item4 = ItemHelper.criarItemDTO();
        item4.setId(id);
        item4.setNome("Hot Dog");

        ItemDTO item5 = ItemHelper.criarItemDTO();
        item5.setId(id);
        item5.setPreco(new BigDecimal(10.50));

        ItemDTO item6 = ItemHelper.criarItemDTO();
        item6.setId(id);
        item6.setCategoria(CategoriaItem.SOBREMESA);

        ItemDTO item7 = ItemHelper.criarItemDTO();
        item7.setId(id);

        ItemDTO itemSemId1 = ItemHelper.criarItemDTO();
        itemSemId1.setId(null);

        ItemDTO itemSemId2 = ItemHelper.criarItemDTO();
        itemSemId2.setId(null);

        // Act & Assert
        // Mesmos valores
        assertTrue(item1.equals(item2), "Objetos com os mesmos atributos devem ser iguais.");

        // Valores diferentes
        assertFalse(item1.equals(item3), "Objetos com atributos diferentes não devem ser iguais.");
        assertFalse(item1.equals(item4), "Objetos com atributos diferentes não devem ser iguais.");
        assertFalse(item1.equals(item5), "Objetos com atributos diferentes não devem ser iguais.");
        assertFalse(item1.equals(item6), "Objetos com atributos diferentes não devem ser iguais.");

        // Comparação com null
        assertFalse(item1.equals(null), "Comparação com null deve retornar false.");

        // Comparação com outro tipo de objeto
        assertFalse(item1.equals(new Object()), "Comparação com outro tipo deve retornar false.");

        // Reflexividade
        assertTrue(item1.equals(item1), "Um objeto deve ser igual a si mesmo.");

        // Simetria
        assertTrue(item2.equals(item1), "Se A é igual a B, então B deve ser igual a A.");

        // Transitividade
        assertTrue(item1.equals(item2) && item2.equals(item7) && item1.equals(item7), 
            "Se A é igual a B e B é igual a C, então A deve ser igual a C.");

        // Consistência
        assertTrue(item1.equals(item2), "Resultado de equals deve ser consistente para objetos imutáveis.");
        assertTrue(item1.equals(item2), "Resultado de equals deve ser consistente para objetos imutáveis.");

        // Verificando com null id
        assertTrue(itemSemId1.equals(itemSemId2), "Objetos com ID nulo mas mesmos atributos devem ser iguais.");
    }
}