package br.com.alfac.foodproduto.core.application.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.domain.item.CategoriaItem;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import utils.ItemHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ConsultarItensPorCategoriaUseCaseTest {

    private ConsultarItensPorCategoriaUseCase consultarItensPorCategoriaUseCase;

    @Mock
    private RepositorioItemGateway repositorioItemGateway;

    @BeforeEach
    void setUp() {
        consultarItensPorCategoriaUseCase = new ConsultarItensPorCategoriaUseCase(repositorioItemGateway);
    }

    @Test
    void devePermitirListarItensPorCategoria() throws FoodProdutoException {
        //Arrange
        List<Item> itens = Arrays.asList(
                ItemHelper.criarItem(),
                ItemHelper.criarItem()
        );

        when(repositorioItemGateway.consultarItensPorCategoria(any(CategoriaItem.class))).thenReturn(itens);

        //Act
        List<Item> itensObtidos = consultarItensPorCategoriaUseCase.execute(CategoriaItem.LANCHE);

        //Assert
        assertThat(itensObtidos).hasSize(2);

        /*assertThat(itensObtidos.getContent())
        .asList()
        .allSatisfy(mensagem -> {
          assertThat(mensagem).isNotNull();
          assertThat(mensagem).isInstanceOf(Mensagem.class);
        });*/

        verify(repositorioItemGateway, times(1)).consultarItensPorCategoria(any(CategoriaItem.class));
    }

    @Test
    void deveGerarExcecao_QuandoListarItensPorCategoria_ItensNaoEncontrados() throws FoodProdutoException {
        //Arrange
        List<Item> itens = Collections.emptyList();
        
        when(repositorioItemGateway.consultarItensPorCategoria(any(CategoriaItem.class))).thenReturn(itens);

        //Act/Assert
        assertThatThrownBy(() -> consultarItensPorCategoriaUseCase.execute(CategoriaItem.LANCHE))
                .isInstanceOf(FoodProdutoException.class)
                .hasMessage("Categoria sem itens cadastrados");
    }

}