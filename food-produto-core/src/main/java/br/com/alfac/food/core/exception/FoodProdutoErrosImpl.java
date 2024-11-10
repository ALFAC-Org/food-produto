package br.com.alfac.food.core.exception;

public abstract class FoodProdutoErrosImpl implements FoodProdutoError {



    private final String appPrefix;
    private final String errorCode;
    private final String errorMessage;
    private final StatusCode statusCode;

    protected FoodProdutoErrosImpl(final String appPrefix, final String errorCode, final String errorMessage) {
        this.appPrefix = appPrefix;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.statusCode = StatusCode.BAD_REQUEST;
    }

    protected FoodProdutoErrosImpl(final String appPrefix, final String errorCode, final String errorMessage, final StatusCode statusCode) {
        this.appPrefix = appPrefix;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    @Override
    public String getErrorCode() {
        return  appPrefix + "-" + errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public int getStatusCode() {
        return statusCode.getCode();
    }
}
