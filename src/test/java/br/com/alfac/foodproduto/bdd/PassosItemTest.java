package br.com.alfac.foodproduto.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import utils.ItemHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.alfac.foodproduto.core.domain.Item;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class PassosItemTest {

    private Response response;

    private Item itemResponse;

    private final String ENDPOINT_ITENS = "http://localhost:8080/api/v1/itens";

    @Quando("submeter um novo item")
    public Item submeterNovoItem() {
        var itemRequest = ItemHelper.criarItemRequest();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(itemRequest)
                .when().post(ENDPOINT_ITENS);
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
                .get("/{id}", itemResponse.getId().toString());
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
                .get();
    }

    @Então("os itens são exibidos com sucesso")
    public void itensSaoExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ItemResponseSchema.json"))
                .body("number", equalTo(0))
                .body("size", equalTo(10));
    }

    @Quando("requisitar a alteração do item")
    public void requisitarAlteracaoDaMensagem() {
        itemResponse.setNome("novo conteudo");
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(itemResponse)
                .when()
                .put("/{id}", itemResponse.getId().toString());
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
                .delete("/{id}", itemResponse.getId().toString());
    }

    @Então("o item é removido com sucesso")
    public void itemRemovidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo("mensagem removida"));
    }

}