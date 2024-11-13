package br.com.alfac.foodproduto.infra.gateways;

import br.com.alfac.foodproduto.core.domain.item.CategoriaItem;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
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
        var itemObtido = repositorioItemGateway.consultarItemPorId(randomId);

        //Assert
        assertThat(itemObtido)
            .isPresent()
            .containsSame(item);

        verify(itemEntityRepository, times(1)).findById(anyLong());
        verify(itemEntityMapper, times(1)).toDomain(any(ItemEntity.class));
    }

}
