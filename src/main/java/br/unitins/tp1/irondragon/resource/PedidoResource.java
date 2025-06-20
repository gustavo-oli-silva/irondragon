package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.PedidoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.EstadoResponseDTO;
import br.unitins.tp1.irondragon.dto.response.PageResponse;
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
    @RolesAllowed({ "Super", "Admin" })
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        LOGGER.info("Método findAll foi executado");

        Long count = pedidoService.count();
        
        PageResponse<PedidoResponseDTO> pageResponse = PageResponse.valueOf(page, pageSize, count,
                pedidoService.findAll(page, pageSize).stream().map(PedidoResponseDTO::valueOf).toList());
        return Response
                .ok(pageResponse)
                .build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "User" })
    public Response findByIdAndUsername(@PathParam("id") Long id) {
        String username = jwt.getClaim("preferred_username");
        LOGGER.info("O cliente " + username + " foi executado com o parametro " + id);

        return Response.ok(PedidoResponseDTO.valueOf(pedidoService.findByUsername(id, username))).build();
    }

    @GET
    @Path("/lista")
    @RolesAllowed({ "User" })
    public Response listByUsername(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        String username = jwt.getClaim("preferred_username");

        LOGGER.info("O Cliente " + username + "pediu uma lista de seus pedidos");

        Long totalCount = pedidoService.countByUsername(username);
        PageResponse<PedidoBasicResponseDTO> pageResponse = PageResponse.valueOf(page, pageSize, totalCount,
                pedidoService.listByUsername(username, page, pageSize).stream().map(PedidoBasicResponseDTO::valueOf)
                        .toList());
        return Response.ok(pageResponse)
                .build();
    }

    @POST
    @RolesAllowed({ "User" })
    public Response create(@Valid PedidoRequestDTO dto) {
        String username = jwt.getClaim("preferred_username");

        LOGGER.info("Método create foi executado, pedido: " + dto);

        return Response.ok(PedidoResponseDTO.valueOf(pedidoService.create(dto, username))).build();
    }

    @PATCH
    @Path("/cancelar/{pedido}")
    @RolesAllowed({ "User" })
    public Response cancelPedido(@PathParam("pedido") Long id) {
        String username = jwt.getClaim("preferred_username");

        LOGGER.info("O pedido " + id + "foi cancelado pelo usuario " + username);

        pedidoService.cancelPedido(id, username);

        return Response.noContent().build();
    }
}