package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.TelefoneRequestDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.EmailUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.dto.response.usuario.UsuarioResponseDTO;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.service.jwt.JwtService;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class UsuarioResourceTest {
    @Inject
    public UsuarioService usuarioService;

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testCreate() {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(
                "TESTE",
                "teste"
        );

        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO(
                "teste",
                "teste@gmail.com",
                "123",
                "938.309.220-35",
                LocalDate.now(),
                telefoneRequestDTO
        );

        given()
                .contentType(ContentType.JSON)
                .body(usuarioRequestDTO)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "username", is("teste"),
                        "email", is("teste@gmail.com"),
                        "cpf", is("938.309.220-35")
                );

        Usuario usuario = usuarioService.findByUsername("teste");

        usuarioService.delete(usuario.getId());
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testDelete() {
        TelefoneRequestDTO telefoneRequestDTO = new TelefoneRequestDTO(
                "TESTE",
                "teste"
        );

        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO(
                "testedelete",
                "testedelete@gmail.com",
                "123",
                "679.848.670-38",
                LocalDate.now(),
                telefoneRequestDTO
        );

        Long id = usuarioService.create(usuarioRequestDTO).getId();

        given()
                .when()
                .delete("/usuarios/{id}", id)
                .then()
                .statusCode(204);

        assertThrows(ValidationException.class, () -> usuarioService.delete(id));
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindById() {
        given()
                .when()
                .get("/usuarios/{id}", 1L)
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "username", is("Ahri")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindAll() {
        given()
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200);
    }
}
