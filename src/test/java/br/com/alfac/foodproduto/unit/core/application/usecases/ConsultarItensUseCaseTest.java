package br.com.alfac.foodproduto.unit.core.application.usecases;

import br.com.alfac.foodproduto.core.application.usecases.ConsultarItensUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import utils.ItemHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ConsultarItensUseCaseTest {

    private ConsultarItensUseCase consultarItensUseCase;

    @Mock
    private RepositorioItemGateway repositorioItemGateway;

    @BeforeEach
    void setUp() {
        consultarItensUseCase = new ConsultarItensUseCase(repositorioItemGateway);
    }

    @Test
    void devePermitirListarItens() throws FoodProdutoException {
        //Arrange
        Item item1 = ItemHelper.criarItem();
        Item item2 = ItemHelper.criarItem();
        List<Item> itens = Arrays.asList(item1, item2);

        when(repositorioItemGateway.consultarItens()).thenReturn(itens);

        //Act
        List<Item> itensObtidos = consultarItensUseCase.execute();

        //Assert
        assertThat(itensObtidos)
            .hasSize(2)
            .containsExactlyInAnyOrder(item1, item2);

        verify(repositorioItemGateway, times(1)).consultarItens();
    }

}