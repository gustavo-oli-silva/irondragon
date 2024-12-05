package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.irondragon.dto.request.TelefoneRequestDTO;
import br.unitins.tp1.irondragon.model.Fornecedor;
import br.unitins.tp1.irondragon.service.fornecedor.FornecedorService;
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
public class FornecedorResourceTest {
    @Inject
    public FornecedorService fornecedorService;

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindAll() {
        given()
                .when()
                .get("/fornecedores")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindById() {
        Long id = 1L;

        Fornecedor fornecedor = fornecedorService.findById(id);

        given()
                .when()
                .get("/fornecedores/{id}", id)
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "nome", is(fornecedor.getNome()),
                        "email", is(fornecedor.getEmail())
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindByNome() {
        given()
                .when()
                .get("fornecedores/search/{nome}", "Terabyte")
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "nome", hasItem("Terabyte"),
                        "email", hasItem("suporte@terabyte.com")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testCreate() {
        TelefoneRequestDTO telefone = new TelefoneRequestDTO(
                "TESTE",
                "teste"
        );

        FornecedorRequestDTO fornecedorRequestDTO = new FornecedorRequestDTO(
                "Teste",
                "support@teste.com",
                telefone
        );

        given()
                .contentType(ContentType.JSON)
                .body(fornecedorRequestDTO)
                .when()
                .post("/fornecedores")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Teste")
                );

        fornecedorService.delete(fornecedorService.findByNome("Teste").getFirst().getId());
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testUpdate() {
        TelefoneRequestDTO telefone = new TelefoneRequestDTO(
                "TESTE",
                "teste"
        );

        FornecedorRequestDTO fornecedorRequestDTO = new FornecedorRequestDTO(
                "Teste",
                "support@teste.com",
                telefone
        );

        Long id = fornecedorService.create(fornecedorRequestDTO).getId();

        FornecedorRequestDTO fornecedorUpdateDTO = new FornecedorRequestDTO(
                "Teste2004",
                "support@maisteste.com",
                telefone
        );

        given()
                .contentType(ContentType.JSON)
                .body(fornecedorUpdateDTO)
                .when()
                .put("/fornecedores/{id}", id)
                .then()
                .statusCode(204);

        Fornecedor fornecedor = fornecedorService.findById(id);

        assertEquals(fornecedor.getNome(), fornecedorUpdateDTO.nome());
        assertEquals(fornecedor.getEmail(), fornecedorUpdateDTO.email());

        fornecedorService.delete(id);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testDelete() {
        TelefoneRequestDTO telefone = new TelefoneRequestDTO(
                "TESTE",
                "teste"
        );

        FornecedorRequestDTO fornecedorRequestDTO = new FornecedorRequestDTO(
                "Teste",
                "support@teste.com",
                telefone
        );

        Long id = fornecedorService.create(fornecedorRequestDTO).getId();

        given()
                .when()
                .delete("fornecedores/{id}", id)
                .then()
                .statusCode(204);

        assertThrows(ValidationException.class, () -> fornecedorService.findById(id));
    }
}
