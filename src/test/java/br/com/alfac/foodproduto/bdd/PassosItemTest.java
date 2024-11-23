package br.com.alfac.foodproduto.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import utils.ItemHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.alfac.foodproduto.core.domain.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest
public class PassosItemTest {

    private Response response;

    private Item itemResponse;

    // @Value("${server.port}")
    // private String appPort;

    private String FULL_ENDPOINT_ITENS;

    @Autowired
    public PassosItemTest(@Value("${server.url}") String baseUrl) {
        this.FULL_ENDPOINT_ITENS = baseUrl + "/api/v1/itens";
    }
    
    @Quando("submeter um novo item")
    public Item submeterNovoItem() {
        System.out.println("FULL_ENDPOINT_ITENS: " + FULL_ENDPOINT_ITENS);
        var itemRequest = ItemHelper.criarItemRequest();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(itemRequest)
                .when().post(FULL_ENDPOINT_ITENS);
        return response.then().extract().as(Item.class);
    }

    @Então("o item é registrado com sucesso")
    public void itemRegistradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ItemResponseSchema.json"));
    }

    @Dado("que um item já foi publicado")
    public void itemJaPublicado() {
        itemResponse = submeterNovoItem();
    }

    @Quando("requisitar a busca do item")
    public void requisitarBuscarItem() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(FULL_ENDPOINT_ITENS + "/por-id/{id}", itemResponse.getId().toString());
    }

    @Então("o item é exibido com sucesso")
    public void itemExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ItemResponseSchema.json"));
    }

    @Quando("requisitar a lista de itens")
    public void requisitarListaItens() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(FULL_ENDPOINT_ITENS);
    }

    @Então("os itens são exibidos com sucesso")
    public void itensSaoExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("requisitar a alteração do item")
    public void requisitarAlteracaoDaMensagem() {
        var itemRequest = ItemHelper.criarItemRequest();
        itemRequest.setNome("Novo Hamburguer");

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(itemRequest)
                .when()
                .put(FULL_ENDPOINT_ITENS + "/{id}", itemResponse.getId().toString());
    }

    @Então("o item é atualizado com sucesso")
    public void itemAtualizadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ItemResponseSchema.json"));
    }

    @Quando("requisitar a exclusão do item")
    public void requisitarExclusaoDoItem() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(FULL_ENDPOINT_ITENS + "/{id}", itemResponse.getId().toString());
    }

    @Então("o item é removido com sucesso")
    public void itemRemovidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ItemResponseSchema.json"));
    }

}
