package br.com.alfac.foodproduto.unit.infra.gateways;

import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import br.com.alfac.foodproduto.infra.gateways.RepositorioItemGatewayImpl;
import br.com.alfac.foodproduto.infra.mapper.ItemEntityMapper;
import br.com.alfac.foodproduto.infra.persistence.ItemEntity;
import br.com.alfac.foodproduto.infra.persistence.ItemEntityRepository;
import utils.ItemHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RepositorioItemGatewayImplTest {

    @Mock
    private ItemEntityRepository itemEntityRepository;

    @Mock
    private ItemEntityMapper itemEntityMapper;
    
    private RepositorioItemGatewayImpl repositorioItemGateway;
 
    @BeforeEach
    void setUp() {
        repositorioItemGateway = new RepositorioItemGatewayImpl(itemEntityRepository, itemEntityMapper);
    }

    @Test
    void devePermitirListarItens() throws FoodProdutoException {
        //Arrange
        Item item1 = ItemHelper.criarItem();
        Item item2 = ItemHelper.criarItem();
        ItemEntity itemEntity1 = ItemHelper.criarItemEntity(item1);
        ItemEntity itemEntity2 = ItemHelper.criarItemEntity(item2);
        List<ItemEntity> itensEntity = Arrays.asList(
            itemEntity1,
            itemEntity2
        );

        when(itemEntityRepository.findAll()).thenReturn(itensEntity);
        when(itemEntityMapper.toDomain(itemEntity1)).thenReturn(item1);
        when(itemEntityMapper.toDomain(itemEntity2)).thenReturn(item2);

        //Act
        var itensObtidos = repositorioItemGateway.consultarItens();

        //Assert
        assertThat(itensObtidos)
            .hasSize(2)
            .containsExactlyInAnyOrder(item1, item2);

        verify(itemEntityRepository, times(1)).findAll();
    }

    @Test
    void devePermitirListarItensPorCategoria() throws FoodProdutoException {
        //Arrange
        CategoriaItem categoria = CategoriaItem.LANCHE;
        Item item1 = ItemHelper.criarItem();
        Item item2 = ItemHelper.criarItem();
        ItemEntity itemEntity1 = ItemHelper.criarItemEntity(item1);
        ItemEntity itemEntity2 = ItemHelper.criarItemEntity(item2);
        List<ItemEntity> itensEntity = Arrays.asList(
            itemEntity1,
            itemEntity2
        );

        when(itemEntityRepository.findByCategoria(categoria)).thenReturn(itensEntity);
        when(itemEntityMapper.toDomain(itemEntity1)).thenReturn(item1);
        when(itemEntityMapper.toDomain(itemEntity2)).thenReturn(item2);

        //Act
        var itensObtidos = repositorioItemGateway.consultarItensPorCategoria(categoria);

        //Assert
        assertThat(itensObtidos)
            .hasSize(2)
            .containsExactlyInAnyOrder(item1, item2);

        verify(itemEntityRepository, times(1)).findByCategoria(any(CategoriaItem.class));
        verify(itemEntityMapper, times(2)).toDomain(any(ItemEntity.class));
    }

    @Test
    void devePermitirBuscarItemPorId() throws FoodProdutoException {
        //Arrange
        Long randomId = ItemHelper.randomId();
        Item item = ItemHelper.criarItem();
        item.setId(randomId);
        ItemEntity itemEntity = ItemHelper.criarItemEntity(item);

        when(itemEntityRepository.findById(anyLong())).thenReturn(Optional.of(itemEntity));
        when(itemEntityMapper.toDomain(itemEntity)).thenReturn(item);

        //Act
        var itemOpcional = repositorioItemGateway.consultarItemPorId(randomId);

        //Assert
        assertThat(itemOpcional)
            .isPresent()
            .containsSame(item);

        itemOpcional.ifPresent(itemArmazenado -> {
            assertThat(itemArmazenado.getId())
                .isEqualTo(item.getId());
            assertThat(itemArmazenado.getNome())
                .isEqualTo(item.getNome());
            assertThat(itemArmazenado.getPreco())
                .isEqualTo(item.getPreco());
            assertThat(itemArmazenado.getCategoria())
                .isEqualTo(item.getCategoria());
            });

        verify(itemEntityRepository, times(1)).findById(anyLong());
        verify(itemEntityMapper, times(1)).toDomain(any(ItemEntity.class));
    }

    @Test
    void devePermitirAtualizarItem() throws FoodProdutoException {
        // Arrange
        Long randomId = ItemHelper.randomId();
        Item item = ItemHelper.criarItem();
        item.setId(randomId);

        ItemDTO itemDTO = ItemHelper.criarItemDTO(item);
        ItemEntity itemEntity = ItemHelper.criarItemEntity(item);

        when(itemEntityRepository.findById(anyLong())).thenReturn(Optional.of(itemEntity));
        when(itemEntityRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);
        when(itemEntityMapper.toDomain(itemEntity)).thenReturn(item);

        // Act
        var itemAtualizado = repositorioItemGateway.atualizarItem(randomId, itemDTO);

        // Assert
        assertThat(itemAtualizado)
            .isInstanceOf(Item.class)
            .isNotNull()
            .isEqualTo(item);

        assertThat(itemAtualizado).extracting(Item::getId).isEqualTo(item.getId());
        assertThat(itemAtualizado).extracting(Item::getNome).isEqualTo(item.getNome());
        assertThat(itemAtualizado).extracting(Item::getPreco).isEqualTo(item.getPreco());
        assertThat(itemAtualizado).extracting(Item::getCategoria).isEqualTo(item.getCategoria());
        
        verify(itemEntityRepository, times(1)).findById(anyLong());
        verify(itemEntityRepository, times(1)).save(any(ItemEntity.class));
        verify(itemEntityMapper, times(1)).toDomain(any(ItemEntity.class));
    }

    @Test
    void devePermitirCadastrarItem() throws FoodProdutoException {
        // Arrange
        Item item = ItemHelper.criarItem();
        ItemEntity itemEntity = ItemHelper.criarItemEntity(item);

        when(itemEntityRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);
        when(itemEntityMapper.toEntity(item)).thenReturn(itemEntity);
        when(itemEntityMapper.toDomain(itemEntity)).thenReturn(item);

        // Act
        var itemSalvo = repositorioItemGateway.cadastrarItem(item);

        // Assert
        assertThat(itemSalvo)
            .isInstanceOf(Item.class)
            .isNotNull()
            .isEqualTo(item);

        assertThat(itemSalvo).extracting(Item::getId).isEqualTo(item.getId());
        assertThat(itemSalvo).extracting(Item::getNome).isEqualTo(item.getNome());
        assertThat(itemSalvo).extracting(Item::getPreco).isEqualTo(item.getPreco());
        assertThat(itemSalvo).extracting(Item::getCategoria).isEqualTo(item.getCategoria());
        
        verify(itemEntityRepository, times(1)).save(any(ItemEntity.class));
        verify(itemEntityMapper, times(1)).toEntity(any(Item.class));
        verify(itemEntityMapper, times(1)).toDomain(any(ItemEntity.class));
    }

    @Test
    void devePermitiExcluirItem() throws FoodProdutoException {
        //Arrange
        Long randomId = ItemHelper.randomId();
        Item item = ItemHelper.criarItem();
        item.setId(randomId);
        ItemEntity itemEntity = ItemHelper.criarItemEntity(item);

        when(itemEntityRepository.findById(anyLong())).thenReturn(Optional.of(itemEntity));
        when(itemEntityMapper.toDomain(itemEntity)).thenReturn(item);
        doNothing().when(itemEntityRepository).delete(itemEntity);

        //Act
        var itemExcluido = repositorioItemGateway.excluirItem(randomId);

        // Assert
        assertThat(itemExcluido).isInstanceOf(Item.class);
        assertThat(item.getId()).isEqualTo(itemExcluido.getId());

        verify(itemEntityRepository, times(1)).findById(anyLong());
        verify(itemEntityRepository, times(1)).delete(any(ItemEntity.class));
        verify(itemEntityMapper, times(1)).toDomain(any(ItemEntity.class));
    }

}
