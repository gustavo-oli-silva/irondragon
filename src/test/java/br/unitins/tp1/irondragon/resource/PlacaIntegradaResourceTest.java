package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.processador.PlacaIntegradaRequestDTO;
import br.unitins.tp1.irondragon.model.processador.PlacaIntegrada;
import br.unitins.tp1.irondragon.service.placaintegrada.PlacaIntegradaService;
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
public class PlacaIntegradaResourceTest {
    @Inject
    public PlacaIntegradaService placaIntegradaService;

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testFindAll() {
        given()
                .when().get("/placasintegradas")
                .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testFindById() {
        given()
                .when().get("/placasintegradas/{id}", 1)
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testFindByNome() {
        given()
                .when().get("/placasintegradas/search/{nome}", "Vega 5")
                .then().statusCode(200)
                .body(
                        "nome", hasItem("Vega 5")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testCreate() {
        PlacaIntegradaRequestDTO placaIntegrada = new PlacaIntegradaRequestDTO("Vega 8", 1.0f, 2.0f, 3.0f);

        given()
                .contentType(ContentType.JSON)
                .body(placaIntegrada)
                .when()
                .post("/placasintegradas")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Vega 8"),
                        "directX", is(1.0f),
                        "openGl", is(2.0f),
                        "vulkan", is(3.0f)
                );

        placaIntegradaService.delete(placaIntegradaService.findByNome("Vega 8").getFirst().getId());
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testUpdate() {
        PlacaIntegradaRequestDTO dto = new PlacaIntegradaRequestDTO("Teste", 1.0f, 2.0f, 3.0f);
        PlacaIntegradaRequestDTO novoDto = new PlacaIntegradaRequestDTO("Outro Teste", 3.0f, 2.0f, 1.0f);

        Long id = placaIntegradaService.create(dto).getId();

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/placasintegradas/" + id)
                .then()
                .statusCode(204);

        PlacaIntegrada fornecedor = placaIntegradaService.findById(id);

        assertEquals(fornecedor.getNome(), "Outro Teste");
        assertEquals(fornecedor.getDirectX(), 3.0f);
        assertEquals(fornecedor.getOpenGl(), 2.0f);
        assertEquals(fornecedor.getVulkan(), 1.0f);

        placaIntegradaService.delete(id);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Super", "Admin"})
    public void testDelete() {
        PlacaIntegradaRequestDTO placaIntegrada = new PlacaIntegradaRequestDTO("Vega 7", 1.0f, 2.0f, 3.0f);

        Long id = placaIntegradaService.create(placaIntegrada).getId();

        given()
                .when()
                .delete("/placasintegradas/" + id)
                .then().statusCode(204);

        assertNull(placaIntegradaService.findById(id));
    }
}
