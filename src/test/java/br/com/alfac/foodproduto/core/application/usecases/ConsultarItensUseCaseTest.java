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
        List<Item> itens = Arrays.asList(
                ItemHelper.criarItem(),
                ItemHelper.criarItem()
        );

        when(repositorioItemGateway.consultarItens()).thenReturn(itens);

        //Act
        List<Item> itensObtidos = consultarItensUseCase.execute();

        //Assert
        assertThat(itensObtidos).hasSize(2);

        /*assertThat(itensObtidos.getContent())
        .asList()
        .allSatisfy(mensagem -> {
          assertThat(mensagem).isNotNull();
          assertThat(mensagem).isInstanceOf(Mensagem.class);
        });*/

        verify(repositorioItemGateway, times(1)).consultarItens();
    }

}