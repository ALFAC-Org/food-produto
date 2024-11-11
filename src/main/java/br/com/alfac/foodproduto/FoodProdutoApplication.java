package br.com.alfac.foodproduto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.alfac.foodproduto")
public class FoodProdutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodProdutoApplication.class, args);
    }

}
