package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.dto.response.FabricanteResponseDTO;
import br.unitins.tp1.irondragon.service.FabricanteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/fabricantes")
public class FabricanteResource {
    @Inject
    public FabricanteService fabricanteService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .ok(FabricanteResponseDTO.valueOf(fabricanteService.findById(id)))
                .build();
    }

    @GET
    public Response findAll() {
        return Response
                .ok(fabricanteService.findAll().stream().map(FabricanteResponseDTO::valueOf).toList())
                .build();
    }

    @POST
    public Response create(@Valid FabricanteRequestDTO dto) {
        return Response
                .status(Response.Status.CREATED)
                .entity(FabricanteResponseDTO.valueOf(fabricanteService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FabricanteRequestDTO dto) {
        fabricanteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        fabricanteService.delete(id);
        return Response.noContent().build();
    }
}
