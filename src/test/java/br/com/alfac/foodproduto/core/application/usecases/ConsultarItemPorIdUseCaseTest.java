package br.com.alfac.foodproduto.core.application.usecases;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarItemPorIdUseCaseTest {

    @Mock
    private RepositorioItemGateway repositorioItemGateway;

    @Test
    void devePermitirBuscarItemPorId() throws FoodProdutoException {
        //Arrange
        Item item = ItemHelper.criarItem();
        long id = item.getId();
        when(repositorioItemGateway.consultarItemPorId(id)).thenReturn(java.util.Optional.of(item));

        //Act
        Item execute = new ConsultarItemPorIdUseCase(repositorioItemGateway).execute(id);

        //Assert
        var itemRetornado = assertThat(execute)
                .isInstanceOf(Item.class);

        itemRetornado
                .extracting(Item::getId)
                .isEqualTo(id);
        itemRetornado
                .extracting(Item::getNome)
                .isEqualTo(item.getNome());
        itemRetornado
                .extracting(Item::getPreco)
                .isEqualTo(item.getPreco());
    }

    @Test
    void deveGerarExcecao_QuandoBuscarItem_IdNaoExistente() throws FoodProdutoException {
        //Arrange
        Item item = ItemHelper.criarItem();
        long id = item.getId();
        when(repositorioItemGateway.consultarItemPorId(id)).thenReturn(java.util.Optional.empty());

        //Act/Assert
        assertThatThrownBy(() -> new ConsultarItemPorIdUseCase(repositorioItemGateway).execute(id))
                .isInstanceOf(FoodProdutoException.class)
                .hasMessage("Item não encontrado");
    }

    @Test
    void execute() throws FoodProdutoException {
        //Arrange
        Item item = ItemHelper.criarItem();
        long id = item.getId();
        when(repositorioItemGateway.consultarItemPorId(id)).thenReturn(java.util.Optional.of(item));

        //Act
        Item execute = new ConsultarItemPorIdUseCase(repositorioItemGateway).execute(id);

        //Assert
        assertNotNull(execute);
    }
}