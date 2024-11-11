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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirItemUseCaseTest {

    private ExcluirItemUseCase excluirItemUseCase;

    @Mock
    private RepositorioItemGateway repositorioItemGateway;

    @BeforeEach
    void setUp() {
        excluirItemUseCase = new ExcluirItemUseCase(repositorioItemGateway);
    }

    @Test
    void devePermitiExcluirItem() throws FoodProdutoException {
        //Arrange
        var id = 1L;
        var item = ItemHelper.criarItem();
        item.setId(id);

        when(repositorioItemGateway.excluirItem(id)).thenReturn(item);

        //Act
        var itemRetornado = excluirItemUseCase.execute(id);

        //Assert
        assertThat(itemRetornado).isInstanceOf(Item.class);
        assertThat(item.getId()).isEqualTo(itemRetornado.getId());
        verify(repositorioItemGateway, times(1)).excluirItem(any(Long.class));
    }

}