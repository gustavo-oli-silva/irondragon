package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.PlacaIntegradaRequestDTO;
import br.unitins.tp1.irondragon.dto.response.PlacaIntegradaResponseDTO;
import br.unitins.tp1.irondragon.service.PlacaIntegradaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/placas-integradas")
public class PlacaIntegradaResource {
    @Inject
    public PlacaIntegradaService placaIntegradaService;

    @GET
    public Response findAll() {
        return Response.ok(placaIntegradaService.findAll().stream().map(PlacaIntegradaResponseDTO::valueOf).toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(PlacaIntegradaResponseDTO.valueOf(placaIntegradaService.findById(id))).build();
    }

    @POST
    @Path("/{id}")
    public Response create(PlacaIntegradaRequestDTO placaIntegrada) {
        return Response.status(Response.Status.CREATED).entity(PlacaIntegradaResponseDTO.valueOf(placaIntegradaService.create(placaIntegrada))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PlacaIntegradaRequestDTO placaIntegrada) {
        placaIntegradaService.update(id, placaIntegrada);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        placaIntegradaService.delete(id);
        return Response.noContent().build();
    }
}
