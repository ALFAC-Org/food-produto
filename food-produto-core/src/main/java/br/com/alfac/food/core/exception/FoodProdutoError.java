package br.com.alfac.food.core.exception;


public interface FoodProdutoError {

    String getErrorCode();

    String getErrorMessage();

    int getStatusCode();
}
