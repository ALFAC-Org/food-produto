package br.com.alfac.foodproduto.core.application.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import utils.ItemHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarItemUseCaseTest {

    private CadastrarItemUseCase cadastrarItemUseCase;

    @Mock
    private RepositorioItemGateway repositorioItemGateway;

    @BeforeEach
    void setUp() {
        cadastrarItemUseCase = new CadastrarItemUseCase(repositorioItemGateway);
    }

    @Test
    void devePermitirCadastrarItem() throws FoodProdutoException {
        //Arrange
        var item = ItemHelper.criarItem();
        var itemDTO = ItemHelper.criarItemDTO(item);
        
        when(repositorioItemGateway.cadastrarItem(any(Item.class)))
            .thenReturn(item);

        //Act
        var itemSalvo = cadastrarItemUseCase.execute(itemDTO);

        //Assert
        assertThat(itemSalvo).isInstanceOf(Item.class).isNotNull();

        assertThat(itemSalvo.getCategoria()).isEqualTo(item.getCategoria());
        assertThat(itemSalvo.getNome()).isEqualTo(item.getNome());
        assertThat(itemSalvo.getPreco()).isEqualTo(item.getPreco());
        assertThat(itemSalvo.getId()).isNotNull();

        verify(repositorioItemGateway, times(1)).cadastrarItem(any(Item.class));
    }

}