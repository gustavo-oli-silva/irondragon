package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.CidadeRequestDTO;
import br.unitins.tp1.irondragon.dto.response.CidadeResponseDTO;
import br.unitins.tp1.irondragon.service.cidade.CidadeService;
import jakarta.annotation.security.RolesAllowed;
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
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Método findByNome foi executado com o parametro [" + nome + "] !");

        return Response
                .ok(
                cidadeService.findByNome(nome).stream().map(CidadeResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    public Response findAll() {
        LOGGER.info("Método findAll foi executado!");

        return Response.ok(cidadeService.findAll()).build();
    }

    @POST
    @RolesAllowed({"Super", "Admin"})
    public Response create(@Valid CidadeRequestDTO cidade) {
        LOGGER.info("Método create foi executado, cidade: " + cidade);

        return Response
                .status(Status.CREATED)
                .entity(CidadeResponseDTO.valueOf(cidadeService.create(cidade)))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, @Valid CidadeRequestDTO cidade) {
        LOGGER.info("Método update foi executado, cidade com id " + id + ": " + cidade);

        cidadeService.update(id, cidade);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        cidadeService.delete(id);
        return Response.noContent().build();
    }
}
