package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.service.estado.EstadoService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class EstadoResourceTest {
    @Inject
    public EstadoService estadoService;
    
    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindAll() {
        given()
                .when().get("/estados")
                .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindById() {
        given()
                .when().get("/estados/{id}", 1)
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindByNome() {
        given()
                .when().get("/estados/search/{nome}", "Tocantins")
                .then().statusCode(200)
                .body(
                        "nome", hasItem("Tocantins")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testCreate() {
        EstadoRequestDTO estado = new EstadoRequestDTO("Teste", "TE");

        given()
                .contentType(ContentType.JSON)
                .body(estado)
                .when()
                .post("/estados")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Teste"),
                        "sigla", is("TE")
                );

        estadoService.delete(estadoService.findByNome("Teste").getFirst().getId());
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testUpdate() {
        EstadoRequestDTO dto = new EstadoRequestDTO("Teste", "TE");
        EstadoRequestDTO novoDto = new EstadoRequestDTO("Outro Teste", "TO");

        Long id = estadoService.create(dto).getId();

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/estados/" + id)
                .then()
                .statusCode(204);

        Estado estado = estadoService.findById(id);

        assertEquals(estado.getNome(), "Outro Teste");
        assertEquals(estado.getSigla(), "TO");

        estadoService.delete(id);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testDelete() {
        EstadoRequestDTO dto = new EstadoRequestDTO("Rio Grande do Sul", "RS");

        Long id = estadoService.create(dto).getId();

        given()
                .when()
                .delete("/estados/" + id)
                .then().statusCode(204);

        assertThrows(ValidationException.class,() -> estadoService.findById(id));
    }
}
