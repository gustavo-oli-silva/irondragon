package br.unitins.tp1.irondragon.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ClienteResourceTest {
    @Test
    @TestSecurity(user = "test", roles = {"Admin"})
    public void testFindAll() {
        given()
                .when()
                .get("/clientes")
                .then()
                .statusCode(200);
    }
}
