package br.unitins.tp1.irondragon.resource;

import org.jboss.logging.Logger;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.EstadoResponseDTO;
import br.unitins.tp1.irondragon.dto.response.PageResponse;
import br.unitins.tp1.irondragon.service.estado.EstadoService;
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
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/estados")
public class EstadoResource {
    private static final Logger LOGGER = Logger.getLogger(EstadoResource.class);

    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    // @RolesAllowed({"Super", "Admin", "User"})
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado!");

        return Response
                .ok(EstadoResponseDTO.valueOf(estadoService.findById(id)))
                .build();
    }

    @GET
    @Path("/search/{nome}")
    //@RolesAllowed({ "Super", "Admin", "User" })
    public Response findByNome(@PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        LOGGER.info("Método findById foi executado com o parametro [" + nome + "]!");

        Long totalCount = estadoService.countByNome(nome);
        PageResponse<EstadoResponseDTO> pageResponse = PageResponse.valueOf(page, pageSize, totalCount,
                estadoService.findByNome(nome,page, pageSize).stream().map(EstadoResponseDTO::valueOf).toList());
        return Response
                .ok(pageResponse)
                .build();
    }

    @GET
    // @RolesAllowed({"Super", "Admin", "User"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        LOGGER.info("Método findAll foi executado!");

        Long totalCount = estadoService.count();
        PageResponse<EstadoResponseDTO> pageResponse = PageResponse.valueOf(page, pageSize, totalCount,
                estadoService.findAll(page, pageSize).stream().map(EstadoResponseDTO::valueOf).toList());
        return Response
                .ok(pageResponse)
                .build();
    }

    @POST
    // @RolesAllowed({"Super", "Admin"})
    public Response create(@Valid EstadoRequestDTO estado) {
        LOGGER.info("Método create foi executado, estado: " + estado);

        return Response
                .status(Status.CREATED).entity(EstadoResponseDTO.valueOf(estadoService.create(estado)))
                .build();
    }

    @PUT
    @Path("/{id}")
    // @RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, @Valid EstadoRequestDTO estado) {
        LOGGER.info("Método update foi executado, estado com id " + id + ": " + estado);

        estadoService.update(id, estado);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    // @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        estadoService.delete(id);
        return Response.noContent().build();
    }
}
