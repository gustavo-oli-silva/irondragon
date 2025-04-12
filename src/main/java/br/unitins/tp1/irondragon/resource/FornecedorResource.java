package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.irondragon.dto.response.FornecedorResponseDTO;
import br.unitins.tp1.irondragon.dto.response.PageResponse;
import br.unitins.tp1.irondragon.service.fornecedor.FornecedorService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/fornecedores")
public class FornecedorResource {
    private static final Logger LOGGER = Logger.getLogger(FornecedorResource.class);

    @Inject
    public FornecedorService fornecedorService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById com o parametro [" + id + "] foi executado!");
        return Response.ok(FornecedorResponseDTO.valueOf(fornecedorService.findById(id))).build();
    }

    @GET
    //@RolesAllowed({"Super", "Admin"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize
    ) {
        LOGGER.info("Método findAll foi executado!");

        Long count = fornecedorService.count();

        return Response
                .ok(PageResponse.valueOf(page, pageSize, count, fornecedorService.findAll(page, pageSize).stream().map(FornecedorResponseDTO::valueOf).toList()))
                .build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize
    ) {
        LOGGER.info("Método findByNome com o parametro [" + nome + "] foi executado!");

        Long count = fornecedorService.count(nome);

        return Response
                .ok(PageResponse.valueOf(page, pageSize, count, fornecedorService.findByNome(nome, page, pageSize).stream().map(FornecedorResponseDTO::valueOf).toList()))
                .build();
    }

    @POST
    //@RolesAllowed({"Super", "Admin"})
    public Response create(@Valid FornecedorRequestDTO dto) {
        LOGGER.info("Método create foi executado, fornecedor: " + dto);

        return Response
                .status(Response.Status.CREATED).entity(FornecedorResponseDTO.valueOf(fornecedorService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FornecedorRequestDTO dto) {
        LOGGER.info("Método update foi executado com o parametro " + id + ", fornecedor: " + dto);

        fornecedorService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    //@RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);
        fornecedorService.delete(id);
        return Response.noContent().build();
    }
}
