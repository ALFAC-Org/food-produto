package br.com.alfac.food.main;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = br.com.alfac.food.FoodProdutoApplication.class)
class FoodProdutoApplicationTest extends BaseTest {

    @Test
    void load() {
        System.out.println("Starting the FoodProdutoApplicationTest file...");
//        System.out.println("SPRING_DATASOURCE_URL: " + System.getenv("SPRING_DATASOURCE_URL"));
//        System.out.println("SPRING_DATASOURCE_USERNAME: " + System.getenv("SPRING_DATASOURCE_USERNAME"));
//        System.out.println("SPRING_DATASOURCE_PASSWORD: " + System.getenv("SPRING_DATASOURCE_PASSWORD"));
//        System.out.println("ENABLE_FLYWAY: " + System.getenv("ENABLE_FLYWAY"));
//        System.out.println("APPLICATION_PORT: " + System.getenv("APPLICATION_PORT"));
//        assertNotNull(System.getenv("SPRING_DATASOURCE_URL"));
//        assertNotNull(System.getenv("SPRING_DATASOURCE_USERNAME"));
//        assertNotNull(System.getenv("SPRING_DATASOURCE_PASSWORD"));
//        assertNotNull(System.getenv("ENABLE_FLYWAY"));
//        assertNotNull(System.getenv("APPLICATION_PORT"));
    }

}