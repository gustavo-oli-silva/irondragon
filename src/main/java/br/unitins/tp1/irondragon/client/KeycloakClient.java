package br.unitins.tp1.irondragon.client;

import br.unitins.tp1.irondragon.dto.request.keycloak.KeycloakUserRequestDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/admin/realms/{realm}/users")
public interface KeycloakClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@HeaderParam("Authorization") String bearerToken,
            @PathParam("realm") String realm, KeycloakUserRequestDTO user);
}