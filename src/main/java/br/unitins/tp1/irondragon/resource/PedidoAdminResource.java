package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.model.pedido.StatusPedido;
import br.unitins.tp1.irondragon.model.usuario.Perfil;
import br.unitins.tp1.irondragon.service.pedido.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pedido-adm")
public class PedidoAdminResource {
    private static final Logger LOGGER = Logger.getLogger(PedidoAdminResource.class);

    @Inject
    public PedidoService pedidoService;

    @PATCH
    @Path("/{pedido}")
    @RolesAllowed({"Super", "Admin"})
    public Response changePedidoStatus(@PathParam("pedido") Long id, @QueryParam("status") StatusPedido statusPedido) {
        LOGGER.info("Pedido " + id + "teve seus status alterado para " + statusPedido);

        pedidoService.updateStatusPedido(id, statusPedido);
        return Response.noContent().build();
    }
}
