package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.EstadoResponseDTO;
import br.unitins.tp1.irondragon.service.estado.EstadoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/estados")
public class EstadoResource {

    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .ok(EstadoResponseDTO.valueOf(estadoService.findById(id)))
                .build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response
                .ok(estadoService.findByNome(nome).stream().map(EstadoResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    public Response findAll() {
        return Response
                .ok(estadoService.findAll().stream().map(EstadoResponseDTO::valueOf).toList())
                .build();
    }

    @POST
    public Response create(@Valid EstadoRequestDTO estado) {
        return Response
                .status(Status.CREATED).entity(EstadoResponseDTO.valueOf(estadoService.create(estado)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid EstadoRequestDTO estado) {
        estadoService.update(id, estado);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        estadoService.delete(id);
        return Response.noContent().build();
    }
}
