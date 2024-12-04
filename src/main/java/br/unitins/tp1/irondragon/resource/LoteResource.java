package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.LoteRequestDTO;
import br.unitins.tp1.irondragon.dto.response.LoteResponseDTO;
import br.unitins.tp1.irondragon.service.lote.LoteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/lotes")
public class LoteResource {
    private static final Logger LOGGER = Logger.getLogger(LoteResource.class);

    @Inject
    public LoteService loteService;

    @GET
    @RolesAllowed({"Super", "Admin"})
    public Response findAll() {
        LOGGER.info("Execução do método findAll");

        return Response.ok(loteService.findAll().stream().map(LoteResponseDTO::valueOf)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Execução do método findById. Id: " + id);

        return Response.ok(LoteResponseDTO.valueOf(loteService.findById(id))).build();
    }

    @GET
    @Path("/search/{codigo}")
    @RolesAllowed({"Super", "Admin"})
    public Response findByCodigo(@PathParam("codigo") String codigo) {
        LOGGER.info("Método findByCodigo foi executado com o parametro " + codigo);

        return Response.ok(LoteResponseDTO.valueOf(loteService.findByCodigo(codigo))).build();
    }

    @POST
    @RolesAllowed({"Super", "Admin"})
    public Response create(@Valid LoteRequestDTO dto) {
        LOGGER.info("Lote " + dto.codigo() + " com " + dto.estoque() + " unidades foi registrado!");
        return Response.status(Response.Status.CREATED).entity(LoteResponseDTO.valueOf(loteService.create(dto))).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, @Valid LoteRequestDTO dto) {
        LOGGER.info("Método update foi executado com o parametro " + id + ", lote: " + dto);

        loteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        loteService.delete(id);
        return Response.noContent().build();
    }
}
