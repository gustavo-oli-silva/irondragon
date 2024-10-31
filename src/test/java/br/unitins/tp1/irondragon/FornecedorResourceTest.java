package br.unitins.tp1.irondragon;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.irondragon.model.Fornecedor;
import br.unitins.tp1.irondragon.service.fornecedor.FornecedorService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class FornecedorResourceTest {
    @Inject
    public FornecedorService fornecedorService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/fornecedores")
                .then().statusCode(200);
    }

    @Test
    public void testFindById() {
        given()
                .when().get("/fornecedores/{id}", 1)
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
                .when().get("/fornecedores/search/{nome}", "Terabyte")
                .then().statusCode(200)
                .body(
                        "nome", hasItem("Terabyte")
                );
    }

    @Test
    public void testCreate() {
        FabricanteRequestDTO fabricante = new FabricanteRequestDTO("Pichau", "suporte@pichau.com");

        given()
                .contentType(ContentType.JSON)
                .body(fabricante)
                .when()
                .post("/fornecedores")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Pichau"),
                        "email", is("suporte@pichau.com")
                );

        fornecedorService.delete(fornecedorService.findByNome("Pichau").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        FornecedorRequestDTO dto = new FornecedorRequestDTO("Teste", "teste@gmail.com");
        FornecedorRequestDTO novoDto = new FornecedorRequestDTO("Outro Teste", "outroteste@gmail.com");

        Long id = fornecedorService.create(dto).getId();

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/fornecedores/" + id)
                .then()
                .statusCode(204);

        Fornecedor fornecedor = fornecedorService.findById(id);

        assertEquals(fornecedor.getNome(), "Outro Teste");
        assertEquals(fornecedor.getEmail(), "outroteste@gmail.com");

        fornecedorService.delete(id);
    }

    @Test
    public void testDelete() {
        FornecedorRequestDTO fornecedor = new FornecedorRequestDTO("Intel", "support@intel.com");

        Long id = fornecedorService.create(fornecedor).getId();

        given()
                .when()
                .delete("/fornecedores/" + id)
                .then().statusCode(204);

        assertNull(fornecedorService.findById(id));
    }
}
