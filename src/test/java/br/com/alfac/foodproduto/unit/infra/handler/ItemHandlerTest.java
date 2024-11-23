package br.com.alfac.foodproduto.unit.infra.handler;

import br.com.alfac.foodproduto.core.application.adapters.controller.ControladorItem;
import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import br.com.alfac.foodproduto.core.exception.ItemError;
import br.com.alfac.foodproduto.infra.config.exception.FoodProdutoExceptionHandler;
import br.com.alfac.foodproduto.infra.dto.ItemRequest;
import br.com.alfac.foodproduto.infra.handler.ItemHandler;
import utils.ItemHelper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

public class ItemHandlerTest {

    private MockMvc mockMvc;
 
    @Mock
    private ControladorItem controladorItem;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ItemHandler itemHandler = new ItemHandler(controladorItem);
        mockMvc = MockMvcBuilders.standaloneSetup(itemHandler)
            .setControllerAdvice(new FoodProdutoExceptionHandler())
            .addFilter((request, response, chain) -> {
                response.setCharacterEncoding("UTF-8");
                chain.doFilter(request, response);
            }, "/*")
        .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }
    
    @Test
    void devePermitirListarItens() throws Exception {
        //Arrange
        ItemDTO itemDTO = ItemHelper.criarItemDTO();
        List<ItemDTO> itemDTOList = Arrays.asList(itemDTO);

        when(controladorItem.consultarItens()).thenReturn(itemDTOList);
      
        //Act/Assert
        mockMvc.perform(get("/api/v1/itens")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(itemDTO.getId().toString()))
                .andExpect(jsonPath("$.[0].nome").value(itemDTO.getNome()))
                .andExpect(jsonPath("$.[0].preco").value(itemDTO.getPreco().setScale(1).toString()))
                .andExpect(jsonPath("$.[0].categoria").value(itemDTO.getCategoria().name()));

            verify(controladorItem, times(1)).consultarItens();
    }

    @Test
    void devePermitirListarItensPorCategoria() throws Exception {
        //Arrange
        CategoriaItem categoriaItem = CategoriaItem.LANCHE;
        ItemDTO itemDTO = ItemHelper.criarItemDTO();
        List<ItemDTO> itemDTOList = Arrays.asList(itemDTO);

        when(controladorItem.consultarItensPorCategoria(categoriaItem)).thenReturn(itemDTOList);
      
        //Act/Assert
        mockMvc.perform(get("/api/v1/itens/por-categoria/{categoria}/itens", categoriaItem.name())
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(itemDTO.getId().toString()))
                .andExpect(jsonPath("$.[0].nome").value(itemDTO.getNome()))
                .andExpect(jsonPath("$.[0].preco").value(itemDTO.getPreco().setScale(1).toString()))
                .andExpect(jsonPath("$.[0].categoria").value(itemDTO.getCategoria().name()));

            verify(controladorItem, times(1)).consultarItensPorCategoria(any(CategoriaItem.class));
    }

    @Test
    void devePermitirBuscarItemPorId() throws Exception {
        //Arrange
        Long randomId = ItemHelper.randomId();
        ItemDTO itemDTO = ItemHelper.criarItemDTO();
        itemDTO.setId(randomId);

        when(controladorItem.consultarItemPorId(anyLong())).thenReturn(itemDTO);
      
        //Act/Assert
        mockMvc.perform(get("/api/v1/itens/por-id/{id}", randomId)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(itemDTO.getId().toString()))
                .andExpect(jsonPath("$.nome").value(itemDTO.getNome()))
                .andExpect(jsonPath("$.preco").value(itemDTO.getPreco().setScale(1).toString()))
                .andExpect(jsonPath("$.categoria").value(itemDTO.getCategoria().name()));

        verify(controladorItem, times(1)).consultarItemPorId(anyLong());
    }

    @Test
    void deveGerarExcecao_QuandoBuscarItem_PorIdInexistente() throws Exception {
        //Arrange
        Long randomId = ItemHelper.randomId();

        when(controladorItem.consultarItemPorId(anyLong()))
            .thenThrow(new FoodProdutoException(ItemError.ITEM_NAO_ENCONTRADO));
      
        //Act/Assert
        mockMvc.perform(get("/api/v1/itens/por-id/{id}", randomId)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(controladorItem, times(1)).consultarItemPorId(anyLong());
    }
    
    @Test
    void devePermitirAtualizarItem() throws Exception {
        //Arrange
        var id = ItemHelper.randomId();
        ItemRequest itemRequest = ItemHelper.criarItemRequest();
        ItemDTO itemDTO = ItemHelper.criarItemDTO(itemRequest);

        when(controladorItem.atualizarItem(anyLong(), any(ItemDTO.class))).thenAnswer(i -> i.getArgument(1));

        //Act/Assert
        mockMvc.perform(put("/api/v1/itens/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(itemRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(itemDTO.getNome()))
                .andExpect(jsonPath("$.preco").value(itemDTO.getPreco().setScale(1).toString()))
                .andExpect(jsonPath("$.categoria").value(itemDTO.getCategoria().name()));

        verify(controladorItem, times(1)).atualizarItem(anyLong(), any(ItemDTO.class));
    }

    @Test
    void devePermitirCadastrarItem() throws Exception {
        //Arrange
        ItemRequest itemRequest = ItemHelper.criarItemRequest();

        when(controladorItem.cadastrarItem(any(ItemDTO.class))).thenAnswer(i -> i.getArgument(0));

        //Act/Assert
        mockMvc.perform(post("/api/v1/itens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(itemRequest)))
                //.andDo(print())
                .andExpect(status().isCreated());
        
        verify(controladorItem, times(1)).cadastrarItem(any(ItemDTO.class));
    }

    @Test
    void devePermitiExcluirItem() throws Exception {
        var id = ItemHelper.randomId();
        ItemDTO itemDTO = ItemHelper.criarItemDTO();
        itemDTO.setId(id);

        when(controladorItem.excluirItem(anyLong())).thenReturn(itemDTO);

        //Act/Assert
        mockMvc.perform(delete("/api/v1/itens/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(itemDTO)))
                //.andDo(print())
                .andExpect(status().isOk());

        verify(controladorItem, times(1)).excluirItem(anyLong());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
