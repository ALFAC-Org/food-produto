package br.com.alfac.food.core.exception;

import java.util.List;

public class FoodProdutoException extends Exception {

    private final FoodProdutoError foodError;
    private final List<FoodProdutoError> foodErrors;

    public FoodProdutoException(final List<FoodProdutoError> foodErrors) {
        super(getMessages(foodErrors));
        this.foodErrors = foodErrors;
        this.foodError = null;
    }

    public FoodProdutoException(FoodProdutoError foodError) {
        super(foodError.getErrorMessage());
        this.foodError = foodError;
        this.foodErrors = null;
    }

    public FoodProdutoException(final List<FoodProdutoError> foodErrors, Throwable e) {
        super(getMessages(foodErrors), e);
        this.foodErrors = foodErrors;
        this.foodError = null;
    }
    public FoodProdutoException(FoodProdutoError foodError, Throwable e) {
        super(foodError.getErrorMessage(), e);
        this.foodError = foodError;
        this.foodErrors = null;
    }


    public FoodProdutoException(FoodProdutoError foodError, Object... args) {
        super(String.format(foodError.getErrorMessage(), args));
        this.foodError = foodError;
        this.foodErrors = null;
    }

    public FoodProdutoException(FoodProdutoError foodError, Throwable e, Object... args) {
        super(String.format(foodError.getErrorMessage(), args), e);
        this.foodError = foodError;
        this.foodErrors = null;
    }

    public FoodProdutoError getFoodErros() {
        return foodError;
    }

    public List<FoodProdutoError> getFoodErrors() {
        return foodErrors;
    }

    private static String getMessages(final List<FoodProdutoError> foodErrors) {
        List<String> mensagensDeErro = foodErrors.stream().map(FoodProdutoError::getErrorMessage).toList();
        return String.join(",", mensagensDeErro);
    }
}
