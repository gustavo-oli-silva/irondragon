package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.processador.*;
import br.unitins.tp1.irondragon.dto.response.processador.MemoriaCacheResponseDTO;
import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class ProcessadorResourceTest {
    @Inject
    public ProcessadorService processadorService;

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindById() {
        given()
                .when()
                .get("/processadores/{id}", 1L)
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "nome", is("I5 12700")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindAll() {
        given()
                .when()
                .get("/processadores")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void findByNome() {
        given()
                .when()
                .get("/processadores/search/{nome}", "I7 7700K")
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "nome", hasItem("I7 7700K")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testCreate() {
        MemoriaCacheRequestDTO memoriaCacheRequestDTO = new MemoriaCacheRequestDTO(
                1.0, 3.0
        );

        ConsumoEnergeticoRequestDTO consumoEnergeticoRequestDTO = new ConsumoEnergeticoRequestDTO(
                65, 100
        );

        FrequenciaRequestDTO frequenciaRequestDTO = new FrequenciaRequestDTO(
                50.0, 50.0
        );

        ConectividadeRequestDTO conectividadeRequestDTO = new ConectividadeRequestDTO(
                4.0f, "DDR5", 4
        );

        ProcessadorRequestDTO processadorRequestDTO = new ProcessadorRequestDTO(
                "Processador de Testes",
                "TESTE5A",
                4,
                4,
                false,
                1000.0,
                1L,
                1L,
                memoriaCacheRequestDTO,
                frequenciaRequestDTO,
                consumoEnergeticoRequestDTO,
                conectividadeRequestDTO
        );

        given()
                .contentType(ContentType.JSON)
                .body(processadorRequestDTO)
                .when()
                .post("/processadores")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Processador de Testes"),
                        "preco", is(1000.0f)
                );

        processadorService.delete(processadorService.findByNome("Processador de Testes").getFirst().getId());
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testUpdate() {
        MemoriaCacheRequestDTO memoriaCacheRequestDTO = new MemoriaCacheRequestDTO(
                1.0, 3.0
        );

        ConsumoEnergeticoRequestDTO consumoEnergeticoRequestDTO = new ConsumoEnergeticoRequestDTO(
                65, 100
        );

        FrequenciaRequestDTO frequenciaRequestDTO = new FrequenciaRequestDTO(
                50.0, 50.0
        );

        ConectividadeRequestDTO conectividadeRequestDTO = new ConectividadeRequestDTO(
                4.0f, "DDR5", 4
        );

        ProcessadorRequestDTO processadorRequestDTO = new ProcessadorRequestDTO(
                "Processador de Testes",
                "TESTE5A",
                4,
                4,
                false,
                1000.0,
                1L,
                1L,
                memoriaCacheRequestDTO,
                frequenciaRequestDTO,
                consumoEnergeticoRequestDTO,
                conectividadeRequestDTO
        );

        Long id = processadorService.create(processadorRequestDTO).getId();

        ProcessadorRequestDTO processadorUpdateDTO = new ProcessadorRequestDTO(
                "Atualização de Testes",
                "TESTE5A",
                4,
                4,
                false,
                4000.0,
                1L,
                1L,
                memoriaCacheRequestDTO,
                frequenciaRequestDTO,
                consumoEnergeticoRequestDTO,
                conectividadeRequestDTO
        );

        given()
                .contentType(ContentType.JSON)
                .body(processadorUpdateDTO)
                .when()
                .put("/processadores/{id}", id)
                .then()
                .statusCode(204);

        Processador processador = processadorService.findById(id);

        assertEquals(processadorUpdateDTO.nome(), processador.getNome());
        assertEquals(processadorUpdateDTO.preco(), processador.getPreco());

        processadorService.delete(id);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testDelete() {
        MemoriaCacheRequestDTO memoriaCacheRequestDTO = new MemoriaCacheRequestDTO(
                1.0, 3.0
        );

        ConsumoEnergeticoRequestDTO consumoEnergeticoRequestDTO = new ConsumoEnergeticoRequestDTO(
                65, 100
        );

        FrequenciaRequestDTO frequenciaRequestDTO = new FrequenciaRequestDTO(
                50.0, 50.0
        );

        ConectividadeRequestDTO conectividadeRequestDTO = new ConectividadeRequestDTO(
                4.0f, "DDR5", 4
        );

        ProcessadorRequestDTO processadorRequestDTO = new ProcessadorRequestDTO(
                "Processador de Testes",
                "TESTE5A",
                4,
                4,
                false,
                1000.0,
                1L,
                1L,
                memoriaCacheRequestDTO,
                frequenciaRequestDTO,
                consumoEnergeticoRequestDTO,
                conectividadeRequestDTO
        );

        Long id = processadorService.create(processadorRequestDTO).getId();

        given()
                .when()
                .delete("/processadores/{id}", id)
                .then()
                .statusCode(204);

        assertThrows(ValidationException.class, () -> processadorService.delete(id));
    }
}
