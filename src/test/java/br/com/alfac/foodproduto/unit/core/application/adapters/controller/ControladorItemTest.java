package br.com.alfac.foodproduto.unit.core.application.adapters.controller;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.alfac.foodproduto.core.application.adapters.controller.ControladorItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.application.usecases.AtualizarItemUseCase;
import br.com.alfac.foodproduto.core.application.usecases.CadastrarItemUseCase;
import br.com.alfac.foodproduto.core.application.usecases.ConsultarItemPorIdUseCase;
import br.com.alfac.foodproduto.core.application.usecases.ConsultarItensPorCategoriaUseCase;
import br.com.alfac.foodproduto.core.application.usecases.ConsultarItensUseCase;
import br.com.alfac.foodproduto.core.application.usecases.ExcluirItemUseCase;
import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import utils.ItemHelper;

@ExtendWith(MockitoExtension.class)
class ControladorItemTest {

    @Mock
    private RepositorioItemGateway repositorioItemGateway;

    private ControladorItem controladorItem;
 
    @BeforeEach
    void setUp() {
        controladorItem = new ControladorItem(repositorioItemGateway);
    }

    @Test
    void devePermitirListarItens() throws Exception {
        //Arrange
        Item item1 = ItemHelper.criarItem();
        Item item2 = ItemHelper.criarItem();
        List<Item> itemList = Arrays.asList(item1, item2);

        ItemDTO item1DTO = ItemHelper.criarItemDTO(item1);
        ItemDTO item2DTO = ItemHelper.criarItemDTO(item2);

        try (MockedConstruction<ConsultarItensUseCase> mockedConsultarItensUseCase = 
            mockConstruction(ConsultarItensUseCase.class, (mock, context) -> {
                when(mock.execute()).thenReturn(itemList);
            })) {

            //Act
            List<ItemDTO> itensObtidos = controladorItem.consultarItens();

            //Assert
            assertThat(itensObtidos)
                .hasSize(2)
                .containsExactlyInAnyOrder(item1DTO, item2DTO);
        }
    }

    @Test
    void devePermitirListarItensPorCategoria() throws FoodProdutoException {
        //Arrange
        CategoriaItem categoria = CategoriaItem.LANCHE;
        Item item1 = ItemHelper.criarItem();
        Item item2 = ItemHelper.criarItem();
        List<Item> itemList = Arrays.asList(item1, item2);

        ItemDTO item1DTO = ItemHelper.criarItemDTO(item1);
        ItemDTO item2DTO = ItemHelper.criarItemDTO(item2);

        try (MockedConstruction<ConsultarItensPorCategoriaUseCase> mockedConsultarItensPorCategoriaUseCase = 
            mockConstruction(ConsultarItensPorCategoriaUseCase.class, (mock, context) -> {
                when(mock.execute(categoria)).thenReturn(itemList);
            })) {

            //Act
            List<ItemDTO> itensObtidos = controladorItem.consultarItensPorCategoria(categoria);

            //Assert
            assertThat(itensObtidos)
                .hasSize(2)
                .containsExactlyInAnyOrder(item1DTO, item2DTO);
        }
    }

    @Test
    void devePermitirBuscarItemPorId() throws FoodProdutoException {
        //Arrange
        Long randomId = ItemHelper.randomId();
        Item item = ItemHelper.criarItem();
        item.setId(randomId);

        try (MockedConstruction<ConsultarItemPorIdUseCase> mockedConsultarItemPorIdUseCase = 
            mockConstruction(ConsultarItemPorIdUseCase.class, (mock, context) -> {
                when(mock.execute(anyLong())).thenReturn(item);
            })) {

            //Act
            var itemObtido = controladorItem.consultarItemPorId(randomId);

            //Assert
            assertThat(itemObtido).isNotNull();
            assertThat(itemObtido.getId()).isEqualTo(item.getId());
            assertThat(itemObtido.getNome()).isEqualTo(item.getNome());
            assertThat(itemObtido.getPreco()).isEqualTo(item.getPreco());
            assertThat(itemObtido.getCategoria()).isEqualTo(item.getCategoria());
        }
    }

    @Test
    void devePermitirCadastrarItem() throws FoodProdutoException {
        // Arrange
        Item item = ItemHelper.criarItem();
        item.setId(1L);
        ItemDTO itemDTO = ItemHelper.criarItemDTO(item);

        try (MockedConstruction<CadastrarItemUseCase> mockedCadastrarItemUseCase = 
            mockConstruction(CadastrarItemUseCase.class, (mock, context) -> {
                when(mock.execute(any(ItemDTO.class))).thenReturn(item);
            })) {

            // Act
            var itemSalvo = controladorItem.cadastrarItem(itemDTO);

            // Assert
            assertThat(itemSalvo)
                .isInstanceOf(ItemDTO.class)
                .isNotNull()
                .isEqualTo(itemDTO);

            assertThat(itemSalvo).extracting(ItemDTO::getId).isEqualTo(item.getId());
            assertThat(itemSalvo).extracting(ItemDTO::getNome).isEqualTo(item.getNome());
            assertThat(itemSalvo).extracting(ItemDTO::getPreco).isEqualTo(item.getPreco());
            assertThat(itemSalvo).extracting(ItemDTO::getCategoria).isEqualTo(item.getCategoria());
        }
    }

    @Test
    void devePermitirAtualizarItem() throws FoodProdutoException {
        // Arrange
        Long randomId = ItemHelper.randomId();
        Item item = ItemHelper.criarItem();
        item.setId(randomId);
        ItemDTO itemDTO = ItemHelper.criarItemDTO(item);

        try (MockedConstruction<AtualizarItemUseCase> mockedAtualizarItemUseCase = 
            mockConstruction(AtualizarItemUseCase.class, (mock, context) -> {
                when(mock.execute(anyLong(), any(ItemDTO.class))).thenReturn(item);
            })) {

            // Act
            var itemAtualizado = controladorItem.atualizarItem(randomId, itemDTO);

            // Assert
            assertThat(itemAtualizado)
                .isInstanceOf(ItemDTO.class)
                .isNotNull()
                .isEqualTo(itemDTO);

            assertThat(itemAtualizado).extracting(ItemDTO::getId).isEqualTo(item.getId());
            assertThat(itemAtualizado).extracting(ItemDTO::getNome).isEqualTo(item.getNome());
            assertThat(itemAtualizado).extracting(ItemDTO::getPreco).isEqualTo(item.getPreco());
            assertThat(itemAtualizado).extracting(ItemDTO::getCategoria).isEqualTo(item.getCategoria());
        }
    }

    @Test
    void devePermitiExcluirItem() throws FoodProdutoException {
        //Arrange
        Long randomId = ItemHelper.randomId();
        Item item = ItemHelper.criarItem();
        item.setId(randomId);

        try (MockedConstruction<ExcluirItemUseCase> mockedExcluirItemUseCase = 
            mockConstruction(ExcluirItemUseCase.class, (mock, context) -> {
                when(mock.execute(anyLong())).thenReturn(item);
            })) {

            //Act
            var itemExcluido = controladorItem.excluirItem(randomId);

            // Assert
            assertThat(itemExcluido).isInstanceOf(ItemDTO.class);
            assertThat(item.getId()).isEqualTo(itemExcluido.getId());
        }
    }

}
