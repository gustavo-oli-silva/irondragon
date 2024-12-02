package br.unitins.tp1.irondragon.resource.usuario;

import br.unitins.tp1.irondragon.dto.response.processador.ProcessadorResponseDTO;
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

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/search/desejos")
    @RolesAllowed({"User"})
    public Response findListaDeDesejos() {
        String username = jwt.getSubject();

        return Response
                .ok(clienteService.getListaDeDesejos(username).stream().map(ProcessadorResponseDTO::valueOf).toList())
                .build();
    }

    @POST
    @Path("/desejos/{processador}")
    @RolesAllowed({"User"})
    public Response addToListaDeDesejos(@PathParam("processador") Long id) {
        String username = jwt.getSubject();

        return Response
                .status(Response.Status.CREATED)
                .entity(ProcessadorResponseDTO.valueOf(clienteService.addToListaDeDesejos(id, username)))
                .build();
    }

    @DELETE
    @Path("/desejos/{processador}")
    @RolesAllowed({"User"})
    public Response removeFromListaDeDesejos(@PathParam("processador") Long id) {
        String username = jwt.getSubject();

        clienteService.removeFromListaDeDesejos(id, username);

        return Response.noContent().build();
    }
}
