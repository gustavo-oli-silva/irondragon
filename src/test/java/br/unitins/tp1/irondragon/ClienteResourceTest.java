package br.unitins.tp1.irondragon;

import br.unitins.tp1.irondragon.dto.request.ClienteRequestDTO;
import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.model.Cliente;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.resource.ClienteResource;
import br.unitins.tp1.irondragon.service.ClienteService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class ClienteResourceTest {
    @Inject
    public ClienteService clienteService;

    @Test
    @TestHTTPEndpoint(ClienteResource.class)
    public void testFindAll() {
        given()
                .when().get()
                .then().statusCode(200);
    }

    @Test
    public void testFindById() {
        given()
                .when().get("/clientes/{id}", 1)
                .then().statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
                .when().get("/clientes/search/{nome}", "Keanu Reeves")
                .then().statusCode(200)
                .body(
                        "nome", hasItem("Keanu Reeves")
                );
    }

    @Test
    @TestHTTPEndpoint(ClienteResource.class)
    public void testCreate() {
        ClienteRequestDTO cliente = new ClienteRequestDTO(
                "Usuário Teste",
                "teste@test.com",
                "646.619.080-31",
                "senhamuitoforte"
        );

        given()
                .contentType(ContentType.JSON)
                .body(cliente)
                .when().post()
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Usuário Teste"),
                        "email", is("teste@test.com"),
                        "cpf", is("646.619.080-31"),
                        "senha", is("senhamuitoforte")
                );

        clienteService.delete(clienteService.findByNome("Usuário Teste").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        ClienteRequestDTO dto = new ClienteRequestDTO(
                "Usuário Teste",
                "teste@test.com",
                "646.619.080-31",
                "senhamuitoforte"
        );

        ClienteRequestDTO novoDto = new ClienteRequestDTO(
                "Outro Teste",
                "outroteste@test.com",
                "042.364.620-60",
                "outrasenhaforte"
        );

        Long id = clienteService.create(dto).getId();

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/clientes/{id}", id)
                .then()
                .statusCode(204);

        Cliente cliente = clienteService.findById(id);

        assertEquals(cliente.getNome(), "Outro Teste");
        assertEquals(cliente.getEmail(), "outroteste@test.com");
        assertEquals(cliente.getCpf(), "042.364.620-60");
        assertEquals(cliente.getSenha(), "outrasenhaforte");

        clienteService.delete(id);
    }

    @Test
    public void testDelete() {
        ClienteRequestDTO dto = new ClienteRequestDTO(
                "Usuário Teste",
                "teste@test.com",
                "646.619.080-31",
                "senhamuitoforte"
        );

        Long id = clienteService.create(dto).getId();

        given()
                .when()
                .delete("/clientes/{id}", id)
                .then().statusCode(204);

        assertNull(clienteService.findById(id));
    }
}
