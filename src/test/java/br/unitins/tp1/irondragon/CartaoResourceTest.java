package br.unitins.tp1.irondragon;

import br.unitins.tp1.irondragon.dto.request.CartaoRequestDTO;
import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.service.cartao.CartaoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class CartaoResourceTest {
    @Inject
    public CartaoService cartaoService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/cartoes")
                .then().statusCode(200);
    }

    @Test
    public void testFindById() {
        given()
                .when().get("/cartoes/{id}", 1)
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void testCreate() {
        CartaoRequestDTO dto = new CartaoRequestDTO(
                "Cartao Teste",
                "40028922",
                "289.072.840-40",
                LocalDate.parse("2024-02-02"),
                123,
                1
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/cartoes")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nomeTitular", is("Cartao Teste"),
                        "numero", is("40028922"),
                        "cpf", is("289.072.840-40"),
                        "validade", is("2024-02-02"),
                        "cvc", is(123),
                        "tipo.id", is(1)
                );


        Cartao cartao = cartaoService.findByNome("Cartao Teste").getFirst();

        cartaoService.delete(cartao.getId());
    }

    @Test
    public void testDelete() {
        CartaoRequestDTO dto = new CartaoRequestDTO(
                "Cartao Teste",
                "40028922",
                "289.072.840-40",
                LocalDate.parse("2024-02-02"),
                123,
                1
        );

        Long id = cartaoService.create(1L, dto).getId();

        given()
                .when()
                .queryParam("cliente", 1)
                .delete("/cartoes/{id}", id)
                .then().statusCode(204);

        assertNull(cartaoService.findById(id));
    }
}
