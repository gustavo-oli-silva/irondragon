package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.PedidoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.PedidoResponseDTO;
import br.unitins.tp1.irondragon.service.pedido.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pedidos")
public class PedidoResource {
    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    public Response findAll() {
        return Response.ok(pedidoService.findAll().stream().map(PedidoResponseDTO::valueOf)).build();
    }

    @GET
    @Path("/search")
    @RolesAllowed({"User"})
    public Response findByUsername() {
        String username = jwt.getSubject();

        return Response.ok(PedidoResponseDTO.valueOf(pedidoService.findByUsername(username))).build();
    }

    @POST
    @RolesAllowed({"User"})
    public Response create(@Valid PedidoRequestDTO dto) {
        String username = jwt.getSubject();

        return Response.ok(PedidoResponseDTO.valueOf(pedidoService.create(dto, username))).build();
    }
}