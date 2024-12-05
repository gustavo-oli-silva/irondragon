package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.dto.request.TelefoneRequestDTO;
import br.unitins.tp1.irondragon.model.Fabricante;
import br.unitins.tp1.irondragon.service.fabricante.FabricanteService;
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
public class FabricanteResourceTest {

    @Inject
    public FabricanteService fabricanteService;

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindAll() {
        given()
                .when()
                .get("/fabricantes")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindById() {
        Long id = 1L;

        Fabricante fabricante = fabricanteService.findById(id);

        given()
                .when()
                .get("/fabricantes/{id}", id)
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "nome", is(fabricante.getNome()),
                        "email", is(fabricante.getEmail())
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindByNome() {
        given()
                .when()
                .get("fabricantes/search/{nome}", "Intel")
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "nome", hasItem("Intel"),
                        "email", hasItem("support@intel.com")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testCreate() {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(
                "TESTE",
                "teste"
        );

        FabricanteRequestDTO fabricanteRequestDTO = new FabricanteRequestDTO(
                "Teste",
                "suporte@teste.com",
                telefoneRequestDTO
        );

        given()
                .contentType(ContentType.JSON)
                .body(fabricanteRequestDTO)
                .when()
                .post("/fabricantes")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Teste"),
                        "email", is("suporte@teste.com")
                );

        fabricanteService.delete(fabricanteService.findByNome("Teste").getFirst().getId());
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testUpdate() {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(
                "TESTE",
                "teste"
        );

        FabricanteRequestDTO fabricanteRequestDTO = new FabricanteRequestDTO(
                "Teste",
                "suporte@teste.com",
                telefoneRequestDTO
        );

        Long id = fabricanteService.create(fabricanteRequestDTO).getId();

        FabricanteRequestDTO fabricanteUpdateDTO = new FabricanteRequestDTO(
                "SegundoTeste",
                "suporte@maisteste.com",
                telefoneRequestDTO
        );

        given()
                .contentType(ContentType.JSON)
                .body(fabricanteUpdateDTO)
                .when()
                .put("/fabricantes/{id}", id)
                .then()
                .statusCode(204);

        Fabricante fabricante = fabricanteService.findById(id);

        assertEquals(fabricante.getNome(), fabricanteUpdateDTO.nome());
        assertEquals(fabricante.getEmail(), fabricanteUpdateDTO.email());

        fabricanteService.delete(id);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testDelete() {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(
                "TESTE",
                "teste"
        );

        FabricanteRequestDTO fabricanteRequestDTO = new FabricanteRequestDTO(
                "Teste",
                "suporte@teste.com",
                telefoneRequestDTO
        );

        Long id = fabricanteService.create(fabricanteRequestDTO).getId();

        given()
                .when()
                .delete("/fabricantes/{id}", id)
                .then()
                .statusCode(204);

        assertThrows(ValidationException.class, () -> fabricanteService.delete(id));
    }
}
