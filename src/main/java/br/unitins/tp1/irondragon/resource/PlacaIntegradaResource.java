package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.processador.PlacaIntegradaRequestDTO;
import br.unitins.tp1.irondragon.dto.response.PageResponse;
import br.unitins.tp1.irondragon.dto.response.processador.PlacaIntegradaResponseDTO;
import br.unitins.tp1.irondragon.service.placaintegrada.PlacaIntegradaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/placasintegradas")
public class PlacaIntegradaResource {
    private static final Logger LOGGER = Logger.getLogger(PlacaIntegradaResource.class);

    @Inject
    public PlacaIntegradaService placaIntegradaService;

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize
    ) {
        LOGGER.info("Método findAll foi executado!");

        Long count = placaIntegradaService.count();

        return Response.ok(
                PageResponse.valueOf(page, pageSize, count, placaIntegradaService.findAll(page, pageSize).stream().map(PlacaIntegradaResponseDTO::valueOf).toList())
        ).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado com o parametro " + id);

        return Response.ok(PlacaIntegradaResponseDTO.valueOf(placaIntegradaService.findById(id).get())).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize
    ) {
        LOGGER.info("Método findByNome foi executado com o parametro " + nome);

        Long count = placaIntegradaService.count(nome);

        return Response
                .ok(PageResponse.valueOf(page, pageSize, count, placaIntegradaService.findByNome(nome, page, pageSize).stream().map(PlacaIntegradaResponseDTO::valueOf).toList()))
                .build();
    }

    @POST
    @RolesAllowed({"Super", "Admin"})
    public Response create(PlacaIntegradaRequestDTO placaIntegrada) {
        LOGGER.info("Método create foi executado , Placa integrada: " + placaIntegrada);

        return Response.status(Response.Status.CREATED).entity(PlacaIntegradaResponseDTO.valueOf(placaIntegradaService.create(placaIntegrada))).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, PlacaIntegradaRequestDTO placaIntegrada) {
        LOGGER.info("Método update foi executado com o parametro " + id + ", Placa integrada: " + placaIntegrada);

        placaIntegradaService.update(id, placaIntegrada);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);
        placaIntegradaService.delete(id);
        return Response.noContent().build();
    }
}
