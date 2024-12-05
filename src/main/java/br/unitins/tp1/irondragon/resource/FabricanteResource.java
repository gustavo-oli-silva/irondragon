package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.dto.response.FabricanteResponseDTO;
import br.unitins.tp1.irondragon.service.fabricante.FabricanteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/fabricantes")
public class FabricanteResource {
    private static final Logger LOGGER = Logger.getLogger(FabricanteResource.class);

    @Inject
    public FabricanteService fabricanteService;

    @GET
    @RolesAllowed({"Super", "Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado!");

        return Response
                .ok(FabricanteResponseDTO.valueOf(fabricanteService.findById(id)))
                .build();
    }

    @GET
    @RolesAllowed({"Super", "Admin"})
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Método findById foi executado com o parametro " + nome);

        return Response
                .ok(
                        fabricanteService.findByNome(nome).stream().map(FabricanteResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    @RolesAllowed({"Super", "Admin"})
    public Response findAll() {
        LOGGER.info("Método findAll foi executado!");

        return Response
                .ok(fabricanteService.findAll().stream().map(FabricanteResponseDTO::valueOf).toList())
                .build();
    }

    @POST
    @RolesAllowed({"Super", "Admin"})
    public Response create(@Valid FabricanteRequestDTO dto) {
        LOGGER.info("Método create foi executado, fabricante: " + dto);

        return Response
                .status(Response.Status.CREATED)
                .entity(FabricanteResponseDTO.valueOf(fabricanteService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, @Valid FabricanteRequestDTO dto) {
        LOGGER.info("Método create foi executado com o parametro " + id + ", fabricante: " + dto);

        fabricanteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        fabricanteService.delete(id);
        return Response.noContent().build();
    }
}
