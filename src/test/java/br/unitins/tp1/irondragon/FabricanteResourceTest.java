package br.unitins.tp1.irondragon;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.model.Fabricante;
import br.unitins.tp1.irondragon.service.FabricanteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class FabricanteResourceTest {
    @Inject
    public FabricanteService fabricanteService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/fabricantes")
                .then().statusCode(200);
    }

    @Test
    public void testFindById() {
        given()
                .when().get("/fabricantes/{id}", 1)
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
                .when().get("/fabricantes/search/{nome}", "Intel")
                .then().statusCode(200)
                .body(
                        "nome", hasItem("Intel")
                );
    }

    @Test
    public void testCreate() {
        FabricanteRequestDTO fabricante = new FabricanteRequestDTO("Qualcomm", "support@qualcomm.com");

        given()
                .contentType(ContentType.JSON)
                .body(fabricante)
                .when()
                .post("/fabricantes")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Qualcomm"),
                        "email", is("support@qualcomm.com")
                );

        fabricanteService.delete(fabricanteService.findByNome("Qualcomm").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        FabricanteRequestDTO dto = new FabricanteRequestDTO("Teste", "teste@gmail.com");
        FabricanteRequestDTO novoDto = new FabricanteRequestDTO("Outro Teste", "outroteste@gmail.com");

        Long id = fabricanteService.create(dto).getId();

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/fabricantes/" + id)
                .then()
                .statusCode(204);

        Fabricante fabricante = fabricanteService.findById(id);

        assertEquals(fabricante.getNome(), "Outro Teste");
        assertEquals(fabricante.getEmail(), "outroteste@gmail.com");

        fabricanteService.delete(id);
    }

    @Test
    public void testDelete() {
        FabricanteRequestDTO fabricante = new FabricanteRequestDTO("Intel", "support@intel.com");

        Long id = fabricanteService.create(fabricante).getId();

        given()
                .when()
                .delete("/fabricantes/" + id)
                .then().statusCode(204);

        assertNull(fabricanteService.findById(id));
    }
}
