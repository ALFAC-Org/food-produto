package br.com.alfac.foodproduto.core.exception;


public class ItemError extends FoodProdutoErrosImpl {
    public static final FoodProdutoError ITEM_NAO_ENCONTRADO = new ItemError("001", "Item não encontrado", StatusCode.NOT_FOUND);
    public static final FoodProdutoError CATEGORIA_SEM_ITENS = new ItemError("002", "Categoria sem itens cadastrados", StatusCode.NOT_FOUND);
    public static final FoodProdutoError ITEM_PEDIDO_INEXISTENTE = new ItemError("008", "Item do pedido não existe %d");

    private static final String APP_PREFIX = "ITEM";

    public ItemError(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public ItemError(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }
}
