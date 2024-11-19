package br.com.alfac.foodproduto.core.exception;


public interface FoodProdutoError {

    String getErrorCode();

    String getErrorMessage();

    int getStatusCode();
}
