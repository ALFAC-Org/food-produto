package br.com.alfac.foodproduto.core.exception.item;

import br.com.alfac.foodproduto.core.exception.FoodProdutoError;
import br.com.alfac.foodproduto.core.exception.FoodProdutoErrosImpl;
import br.com.alfac.foodproduto.core.exception.StatusCode;

public class ItemError extends FoodProdutoErrosImpl {
    public static final FoodProdutoError ITEM_NAO_ENCONTRADO = new ItemError("001", "Item não encontrado", StatusCode.NOT_FOUND);
    public static final FoodProdutoError CATEGORIA_SEM_ITENS = new ItemError("002", "Categoria sem itens cadastrados", StatusCode.NOT_FOUND);
    public static final FoodProdutoError CATEGORIA_ITEM_ACOMPANHAMENTO_INVALIDA = new ItemError("003", "Categoria do item acompanhamento está inválida");
    public static final FoodProdutoError CATEGORIA_ITEM_LANCHE_INVALIDA = new ItemError("004", "Categoria do item lanche está inválida");
    public static final FoodProdutoError CATEGORIA_ITEM_COMPLEMENTO_INVALIDA = new ItemError("005", "Categoria do complemento está inválida");
    public static final FoodProdutoError CATEGORIA_ITEM_BEBIDA_INVALIDA = new ItemError("006", "Categoria do item bebida está inválida");
    public static final FoodProdutoError CATEGORIA_ITEM_SOBREMESA_INVALIDA = new ItemError("007", "Categoria do item sobremesa está inválida");
    public static final FoodProdutoError ITEM_PEDIDO_INEXISTENTE = new ItemError("008", "Item do pedido não existe %d");

    private static final String APP_PREFIX = "ITEM";

    public ItemError(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public ItemError(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }
}