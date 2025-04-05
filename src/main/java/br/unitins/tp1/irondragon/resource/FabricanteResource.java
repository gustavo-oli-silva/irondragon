package br.unitins.tp1.irondragon.resource;

import org.jboss.logging.Logger;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.dto.response.FabricanteResponseDTO;
import br.unitins.tp1.irondragon.dto.response.PageResponse;
import br.unitins.tp1.irondragon.service.fabricante.FabricanteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/fabricantes")
public class FabricanteResource {
    private static final Logger LOGGER = Logger.getLogger(FabricanteResource.class);

    @Inject
    public FabricanteService fabricanteService;

    @GET
    // @RolesAllowed({"Super", "Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado!");

        return Response
                .ok(FabricanteResponseDTO.valueOf(fabricanteService.findById(id)))
                .build();
    }

    @GET
    // @RolesAllowed({"Super", "Admin"})
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        LOGGER.info("Método findById foi executado com o parametro " + nome);

        PageResponse<FabricanteResponseDTO> pageResponse = PageResponse.valueOf(page, pageSize,
                fabricanteService.count(nome),
                fabricanteService.findByNome(nome, page, pageSize).stream().map(FabricanteResponseDTO::valueOf)
                        .toList());
        return Response.ok(pageResponse).build();
    }

    @GET
    // @RolesAllowed({"Super", "Admin"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        LOGGER.info("Método findAll foi executado!");
        PageResponse<FabricanteResponseDTO> pageResponse = PageResponse.valueOf(page, pageSize,
                fabricanteService.count(),
                fabricanteService.findAll(page, pageSize).stream().map(FabricanteResponseDTO::valueOf).toList());
        return Response.ok(pageResponse).build();
    }

    @POST
    // @RolesAllowed({"Super", "Admin"})
    public Response create(@Valid FabricanteRequestDTO dto) {
        LOGGER.info("Método create foi executado, fabricante: " + dto);

        return Response
                .status(Response.Status.CREATED)
                .entity(FabricanteResponseDTO.valueOf(fabricanteService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    // @RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, @Valid FabricanteRequestDTO dto) {
        LOGGER.info("Método create foi executado com o parametro " + id + ", fabricante: " + dto);

        fabricanteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    // @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        fabricanteService.delete(id);
        return Response.noContent().build();
    }
}
