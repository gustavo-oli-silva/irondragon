package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.service.pagamento.PagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pagamentos")
public class PagamentoResource {
    private static final Logger LOGGER = Logger.getLogger(PagamentoResource.class);

    @Inject
    public PagamentoService pagamentoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado com o parametro " + id);
        return Response.ok(pagamentoService.findByIdPedido(id)).build();
    }

    @POST
    @Path("/pix/{pedido}")
    @RolesAllowed({"User"})
    public Response generatePix(@PathParam("pedido") Long pedido) {
        String username = jwt.getSubject();

        LOGGER.info("Método generatePix foi executado com o parametro " + pedido);

        return Response
                .status(Response.Status.CREATED)
                .entity(pagamentoService.generatePix(pedido, username))
                .build();
    }

    @POST
    @Path("/boleto/{pedido}")
    @RolesAllowed({"User"})
    public Response generateBoleto(@PathParam("pedido") Long pedido) {
        String username = jwt.getSubject();

        LOGGER.info("Método generateBoleto foi executado com o parametro " + pedido);

        return Response
                .status(Response.Status.CREATED)
                .entity(pagamentoService.generateBoleto(pedido, username))
                .build();
    }

    @POST
    @Path("/cartao/{cartao}/{pedido}")
    @RolesAllowed({"User"})
    public Response cardPayment(@PathParam("cartao") Long cartao, @PathParam("pedido") Long pedido) {
        String username = jwt.getSubject();

        LOGGER.info("Pagamento com o cartão " + cartao + "foi realizado para o pedido " + pedido);

        return Response
                .status(Response.Status.CREATED)
                .entity(pagamentoService.cardPayment(pedido, cartao, username))
                .build();
    }

    @PATCH
    @Path("{pedido}/boleto/{boleto}")
    @RolesAllowed({"User"})
    public Response boletoPayment(@PathParam("boleto") Long boleto, @PathParam("pedido") Long pedido) {
        String username = jwt.getSubject();

        LOGGER.info("Pagamento com o cartão " + boleto + "foi realizado para o pedido " + pedido);

        pagamentoService.payment(pedido, boleto, username, "boleto");

        return Response.noContent().build();
    }

    @PATCH
    @Path("{pedido}/pix/{pix}")
    @RolesAllowed({"User"})
    public Response pixPayment(@PathParam("pix") Long pix, @PathParam("pedido") Long pedido) {
        String username = jwt.getSubject();

        LOGGER.info("Pagamento com o cartão " + pix + "foi realizado para o pedido " + pedido);

        pagamentoService.payment(pedido, pix, username, "pix");

        return Response.noContent().build();
    }
}
