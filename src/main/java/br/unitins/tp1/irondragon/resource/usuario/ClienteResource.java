package br.unitins.tp1.irondragon.resource.usuario;

import br.unitins.tp1.irondragon.dto.request.usuario.ClienteRequestDTO;
import br.unitins.tp1.irondragon.dto.response.usuario.ClienteResponseDTO;
import br.unitins.tp1.irondragon.service.cliente.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/clientes")
public class ClienteResource {
    @Inject
    public ClienteService clienteService;

    @Inject
    public JsonWebToken jwt;

    @GET
    public Response findAll() {
        return Response.ok(clienteService.findAll().stream().map(ClienteResponseDTO::valueOf)).build();
    }

    @POST
    @RolesAllowed({"User"})
    public Response create(@Valid ClienteRequestDTO dto) {
        String username = jwt.getSubject();

        return Response.status(Response.Status.CREATED).entity(clienteService.create(username, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.noContent().build();
    }
}
