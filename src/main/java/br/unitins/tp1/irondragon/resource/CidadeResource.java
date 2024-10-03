package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.CidadeRequestDTO;
import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.service.CidadeService;
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
@Path("/cidades")
public class CidadeResource {
    @Inject
    public CidadeService cidadeService;
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(cidadeService.findById(id)).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(cidadeService.findByNome(nome)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(cidadeService.findAll()).build();
    }

    @POST
    public Response create(@Valid CidadeRequestDTO cidade) {
        return Response
                .status(Status.CREATED)
                .entity(cidadeService.create(cidade))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CidadeRequestDTO cidade) {
        cidadeService.update(id, cidade);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        cidadeService.delete(id);
        return Response.noContent().build();
    }
}
