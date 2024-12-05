package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.PedidoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.pedido.PedidoBasicResponseDTO;
import br.unitins.tp1.irondragon.dto.response.pedido.PedidoResponseDTO;
import br.unitins.tp1.irondragon.service.pedido.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pedidos")
public class PedidoResource {
    private static final Logger LOGGER = Logger.getLogger(PedidoResource.class);

    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @RolesAllowed({"Super", "Admin"})
    public Response findAll() {
        LOGGER.info("Método findAll foi executado");

        return Response.ok(pedidoService.findAll().stream().map(PedidoResponseDTO::valueOf)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User"})
    public Response findByIdAndUsername(@PathParam("id") Long id) {
        String username = jwt.getSubject();

        LOGGER.info("O cliente " + username + " foi executado com o parametro " + id);

        return Response.ok(PedidoResponseDTO.valueOf(pedidoService.findByUsername(id, username))).build();
    }

    @GET
    @Path("/lista")
    @RolesAllowed({"User"})
    public Response listByUsername() {
        String username = jwt.getSubject();

        LOGGER.info("O Cliente " + username + "pediu uma lista de seus pedidos");

        return Response.ok(pedidoService.listByUsername(username).stream().map(PedidoBasicResponseDTO::valueOf)).build();
    }

    @POST
    @RolesAllowed({"User"})
    public Response create(@Valid PedidoRequestDTO dto) {
        String username = jwt.getSubject();

        LOGGER.info("Método create foi executado, pedido: " + dto);

        return Response.ok(PedidoResponseDTO.valueOf(pedidoService.create(dto, username))).build();
    }

    @PATCH
    @Path("/cancelar/{pedido}")
    @RolesAllowed({"User"})
    public Response cancelPedido(@PathParam("pedido") Long id) {
        String username = jwt.getSubject();

        LOGGER.info("O pedido " + id + "foi cancelado pelo usuario " + username);

        pedidoService.cancelPedido(id, username);

        return Response.noContent().build();
    }
}