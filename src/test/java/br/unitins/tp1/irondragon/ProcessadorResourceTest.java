package br.unitins.tp1.irondragon;

import br.unitins.tp1.irondragon.dto.request.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.model.Processador;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class ProcessadorResourceTest {
    @Inject
    public ProcessadorService processadorService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/placasintegradas")
                .then().statusCode(200);
    }

    @Test
    public void testFindById() {
        given()
                .when().get("/processadores/{id}", 1)
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
                .when().get("/processadores/search/{nome}", "I7 7700K")
                .then().statusCode(200)
                .body(
                        "nome", hasItem("I7 7700K")
                );
    }

    @Test
    public void testCreate() {
        ProcessadorRequestDTO processador = new ProcessadorRequestDTO(
                "Teste",
                "123",
                5,
                5,
                true,
                985.0,
                1L,
                1L
        );

        given()
                .contentType(ContentType.JSON)
                .body(processador)
                .when()
                .post("/processadores")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Teste"),
                        "socket", is("123"),
                        "threads", is(5),
                        "nucleos", is(5),
                        "desbloqueado", is(true),
                        "preco", is(985.0f)
                );

        processadorService.delete(processadorService.findByNome("Teste").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        ProcessadorRequestDTO dto = new ProcessadorRequestDTO(
                "Teste",
                "123",
                5,
                5,
                true,
                985.0,
                1L,
                1L
                );
        ProcessadorRequestDTO novoDto = new ProcessadorRequestDTO(
                "OutroTeste",
                "ABC",
                2,
                3,
                true,
                1000.0,
                1L,
                1L
        );

        Long id = processadorService.create(dto).getId();

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/processadores/" + id)
                .then()
                .statusCode(204);

        Processador processador = processadorService.findById(id);

        assertEquals(processador.getNome(), "OutroTeste");
        assertEquals(processador.getSocket(), "ABC");
        assertEquals(processador.getThreads(), 2);
        assertEquals(processador.getNucleos(), 3);
        assertEquals(processador.getDesbloqueado(), true);
        assertEquals(processador.getPreco(), 1000.0);

        processadorService.delete(id);
    }

    @Test
    public void testDelete() {
        ProcessadorRequestDTO processador = new ProcessadorRequestDTO(
                "Teste",
                "123",
                5,
                5,
                true,
                985.0,
                1L,
                1L
        );

        Long id = processadorService.create(processador).getId();

        given()
                .when()
                .delete("/processadores/{id}", id)
                .then().statusCode(204);

        assertNull(processadorService.findById(id));
    }
}
