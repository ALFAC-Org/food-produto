package br.com.alfac.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.alfac.food")
public class FoodProdutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodProdutoApplication.class, args);
    }

}
