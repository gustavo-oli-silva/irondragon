package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.response.processador.ProcessadorResponseDTO;
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
import br.unitins.tp1.irondragon.dto.request.processador.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/processadores")
public class ProcessadorResource {
    @Inject
    public ProcessadorService processadorService;

    @GET
    public Response findAll() {
        return Response
                .ok(processadorService.findAll().stream().map(ProcessadorResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response
                .ok(processadorService.findByNome(nome).stream().map(ProcessadorResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .ok(ProcessadorResponseDTO.valueOf(processadorService.findById(id)))
                .build();
    }

    @POST
    public Response create(ProcessadorRequestDTO processador) {
        return Response.status(Status.CREATED)
                .entity(ProcessadorResponseDTO.valueOf(processadorService.create(processador)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid ProcessadorRequestDTO processador) {
        processadorService.update(id, processador);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        processadorService.delete(id);
        return Response.noContent().build();
    }
}
