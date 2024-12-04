package br.unitins.tp1.irondragon.resource.usuario;

import br.unitins.tp1.irondragon.dto.request.usuario.FuncionarioRequestDTO;
import br.unitins.tp1.irondragon.dto.response.usuario.FuncionarioResponseDTO;
import br.unitins.tp1.irondragon.resource.ProcessadorResource;
import br.unitins.tp1.irondragon.service.funcionario.FuncionarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/funcionarios")
public class FuncionarioResource {
    private static final Logger LOGGER = Logger.getLogger(FuncionarioResource.class);

    @Inject
    public FuncionarioService funcionarioService;

    @GET
    @RolesAllowed({"Super", "Admin"})
    public Response findAll() {
        LOGGER.info("Método findAll foi executado!");
        return Response.ok(funcionarioService.findAll()).build();
    }

    @POST
    @Path("/{id}")
    @RolesAllowed({"Super"})
    public Response create(@PathParam("id") Long id, FuncionarioRequestDTO dto) {
        LOGGER.info("Método create foi executado com o parametro " + id + ", funcionário: " + dto);

        return Response
                .status(Response.Status.CREATED)
                .entity(FuncionarioResponseDTO.valueOf(funcionarioService.create(id, dto)))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Super"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        funcionarioService.delete(id);
        return Response.noContent().build();
    }
}
