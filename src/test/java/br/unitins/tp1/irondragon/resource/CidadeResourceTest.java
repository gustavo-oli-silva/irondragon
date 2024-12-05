package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.CidadeRequestDTO;
import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.service.cidade.CidadeService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class CidadeResourceTest {

    @Inject
    public CidadeService cidadeService;

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testFindAll() {
        given()
                .when().get("/cidades")
                .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testFindById() {
        given()
                .when().get("/cidades/{id}", 1)
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testFindByNome() {
        given()
                .when().get("/cidades/search/{nome}", "Palmas")
                .then().statusCode(200)
                .body(
                        "nome", hasItem("Palmas")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testCreate() {
        CidadeRequestDTO cidade = new CidadeRequestDTO("Teste", 1L);

        given()
                .contentType(ContentType.JSON)
                .body(cidade)
                .when()
                .post("/cidades")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Teste"),
                        "estado.nome", is("Tocantins"),
                        "estado.sigla", is("TO")
                );

        cidadeService.delete(cidadeService.findByNome("Teste").getFirst().getId());
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testUpdate() {
        CidadeRequestDTO dto = new CidadeRequestDTO("Teste", 1L);
        CidadeRequestDTO novoDto = new CidadeRequestDTO("Outro Teste", 2L);

        Long id = cidadeService.create(dto).getId();

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/cidades/" + id)
                .then()
                .statusCode(204);

        Cidade cidade = cidadeService.findById(id);

        assertEquals(cidade.getNome(), "Outro Teste");

        cidadeService.delete(id);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testDelete() {
        CidadeRequestDTO dto = new CidadeRequestDTO("Teste", 1L);

        Long id = cidadeService.create(dto).getId();

        given()
                .when()
                .delete("/cidades/" + id)
                .then().statusCode(204);

        assertNull(cidadeService.findById(id));
    }
}
