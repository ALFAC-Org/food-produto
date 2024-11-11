package br.com.alfac.foodproduto.core.application.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import utils.ItemHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarItemUseCaseTest {

    private AtualizarItemUseCase atualizarItemUseCase;

    @Mock
    private RepositorioItemGateway repositorioItemGateway;

    @BeforeEach
    void setUp() {
        atualizarItemUseCase = new AtualizarItemUseCase(repositorioItemGateway);
    }

    @Test
    void devePermitirAtualizarItem() throws FoodProdutoException {
        //Arrange
        var id = 1L;
        var itemPreAtualizacao = ItemHelper.criarItem();
        itemPreAtualizacao.setId(id);
        var itemAtualizado = itemPreAtualizacao;
        itemAtualizado.setNome("Novo Hamburguer");
        var itemAtualizadoDTO = ItemHelper.criarItemDTO(itemAtualizado);

        when(repositorioItemGateway.atualizarItem(any(Long.class), any(ItemDTO.class)))
            .thenReturn(itemAtualizado);

        //Act
        var itemObtido = atualizarItemUseCase.execute(id, itemAtualizadoDTO);

        //Assert
        assertThat(itemObtido).isInstanceOf(Item.class).isNotNull();

        assertThat(itemObtido.getId()).isEqualTo(itemAtualizado.getId());
        assertThat(itemObtido.getPreco()).isEqualTo(itemAtualizado.getPreco());
        assertThat(itemObtido.getNome()).isEqualTo(itemAtualizado.getNome());
        assertThat(itemObtido.getCategoria()).isEqualTo(itemAtualizado.getCategoria());

        verify(repositorioItemGateway, times(1)).atualizarItem(any(Long.class), any(ItemDTO.class));
    }
    
}