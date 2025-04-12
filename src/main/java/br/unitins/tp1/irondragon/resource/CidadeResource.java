package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.CidadeRequestDTO;
import br.unitins.tp1.irondragon.dto.response.CidadeResponseDTO;
import br.unitins.tp1.irondragon.dto.response.PageResponse;
import br.unitins.tp1.irondragon.service.cidade.CidadeService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/cidades")
public class CidadeResource {
    private static final Logger LOGGER = Logger.getLogger(CidadeResource.class);

    @Inject
    public CidadeService cidadeService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado com o parametro [" + id + "] !");

        return Response.ok(CidadeResponseDTO.valueOf(cidadeService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize
    ) {
        LOGGER.info("Método findByNome foi executado com o parametro [" + nome + "] !");

        Long count = cidadeService.count(nome);

        return Response.ok(
                PageResponse.
                        valueOf(page, pageSize, count, cidadeService.findByNome(nome, page, pageSize).stream().map(CidadeResponseDTO::valueOf).toList())
        ).build();
    }

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize
    ) {
        LOGGER.info("Método findAll foi executado!");

        Long count = cidadeService.count();

        return Response.ok(
                PageResponse
                        .valueOf(page, pageSize, count, cidadeService.findAll(page, pageSize).stream().map(CidadeResponseDTO::valueOf).toList())
        ).build();
    }

    @POST
    //@RolesAllowed({"Super", "Admin"})
    public Response create(@Valid CidadeRequestDTO cidade) {
        LOGGER.info("Método create foi executado, cidade: " + cidade);

        return Response
                .status(Status.CREATED)
                .entity(CidadeResponseDTO.valueOf(cidadeService.create(cidade)))
                .build();
    }

    @PUT
    @Path("/{id}")
    //@RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, @Valid CidadeRequestDTO cidade) {
        LOGGER.info("Método update foi executado, cidade com id " + id + ": " + cidade);

        cidadeService.update(id, cidade);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    //@RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        cidadeService.delete(id);
        return Response.noContent().build();
    }
}
