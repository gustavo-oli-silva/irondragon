package br.unitins.tp1.irondragon.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.Response;


@Path("/protocol/openid-connect/token")
public interface KeycloakTokenClient {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Response getToken(Form form);
}