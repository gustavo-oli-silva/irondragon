package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.LoteRequestDTO;
import br.unitins.tp1.irondragon.model.pedido.Lote;
import br.unitins.tp1.irondragon.service.lote.LoteService;
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

@QuarkusTest
public class LoteResourceTest {
    @Inject
    public LoteService loteService;

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindAll() {
        given()
                .when()
                .get("/lotes")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindById() {
        Lote lote = loteService.findById(1L);

        given()
                .when()
                .get("/lotes/{id}", 1L)
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "codigo", is(lote.getCodigo())
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindByCodigo() {
        given()
                .when()
                .get("/lotes/search/{codigo}", "#ZXYD4343")
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "codigo", is("#ZXYD4343")
                );
    }

    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testCreate() {
        LoteRequestDTO loteRequestDTO = new LoteRequestDTO(
          1L, LocalDate.now(), "#ABC", 20, 1L
        );

        given()
                .contentType(ContentType.JSON)
                .body(loteRequestDTO)
                .when()
                .post("/lotes")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "codigo", is("#ABC"),
                        "estoque", is(20)
                );

        loteService.delete(loteService.findByCodigo("#ABC").getId());
    }

//    @Test
//    @TestSecurity(user = "test", roles = {"Admin"})
//    public void testUpdate() {
//        LoteRequestDTO loteRequestDTO = new LoteRequestDTO(
//                1L, LocalDate.now(), "#UNITESTEHORROROSO", 20, 1L
//        );
//
//        Long id = loteService.create(loteRequestDTO).getId();
//
//        LoteRequestDTO loteUpdateDTO = new LoteRequestDTO(
//                2L, LocalDate.now(), "#QUALQUERCOISAAAAAAAAA", 20, 1L
//        );
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(loteRequestDTO)
//                .when()
//                .put("/lotes/{id}", id)
//                .then()
//                .statusCode(204);
//
//        Lote lote = loteService.findById(id);
//
//        assertEquals(loteUpdateDTO.codigo(), lote.getCodigo());
//
//        loteService.delete(id);
//    }
}

