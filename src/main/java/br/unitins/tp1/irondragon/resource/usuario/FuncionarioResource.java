package br.unitins.tp1.irondragon.resource.usuario;

import org.jboss.logging.Logger;

import br.unitins.tp1.irondragon.dto.request.usuario.CargoUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.FuncionarioRequestDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.SalarioUpdateDTO;
import br.unitins.tp1.irondragon.dto.response.PageResponse;
import br.unitins.tp1.irondragon.dto.response.usuario.FuncionarioResponseDTO;
import br.unitins.tp1.irondragon.service.funcionario.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
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
@Path("/funcionarios")
public class FuncionarioResource {
    private static final Logger LOGGER = Logger.getLogger(FuncionarioResource.class);

    @Inject
    public FuncionarioService funcionarioService;

    @GET
    //@RolesAllowed({"Super", "Admin"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        LOGGER.info("Método findAll foi executado!");
        PageResponse<FuncionarioResponseDTO> pageResponse = PageResponse.valueOf(page, pageSize,
                funcionarioService.count(),
                funcionarioService.findAll(page, pageSize).stream().map(FuncionarioResponseDTO::valueOf).toList());
        return Response.ok(pageResponse).build();
    }

    @GET
    // @RolesAllowed({"Super", "Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado!");

        return Response
                .ok(FuncionarioResponseDTO.valueOf(funcionarioService.findById(id)))
                .build();
    }
    @POST
    @Path("/{id}")
    //@RolesAllowed({"Super"})
    public Response create(@PathParam("id") Long id, FuncionarioRequestDTO dto) {
        LOGGER.info("Método create foi executado com o parametro " + id + ", funcionário: " + dto);

        return Response
                .status(Response.Status.CREATED)
                .entity(FuncionarioResponseDTO.valueOf(funcionarioService.create(id, dto)))
                .build();
    }

    @PATCH
    @Path("/{id}/salario")
    //@RolesAllowed({"Super"})
    public Response updateSalario(@PathParam("id") Long id, SalarioUpdateDTO dto) {
        LOGGER.info("Salário do Funcionário " + id + " foi alterado para " + dto.salario());

        funcionarioService.updateSalario(id, dto);

        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}/cargo")
    //@RolesAllowed({"Super"})
    public Response updateCargo(@PathParam("id") Long id, CargoUpdateDTO dto) {
        LOGGER.info("Cargo do Funcionário " + id + " foi alterado para " + dto.cargo());

        funcionarioService.updateCargo(id, dto);

        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    //@RolesAllowed({"Super"})
    public Response update(@PathParam("id") Long id, FuncionarioRequestDTO dto) {
        LOGGER.info("Método update foi executado com o parametro " + id + ", funcionario: " + dto);

        funcionarioService.update(id, dto);

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    //@RolesAllowed({"Super"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        funcionarioService.delete(id);
        return Response.noContent().build();
    }
}
