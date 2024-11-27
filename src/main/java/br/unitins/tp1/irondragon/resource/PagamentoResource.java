package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.service.pagamento.PagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pagamentos")
public class PagamentoResource {
    @Inject
    public PagamentoService pagamentoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(pagamentoService.findByIdPedido(id)).build();
    }

    @POST
    @Path("/pix/{pedido}")
    @RolesAllowed({"User"})
    public Response generatePix(@PathParam("pedido") Long pedido) {
        String username = jwt.getSubject();

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

        pagamentoService.payment(pedido, boleto, username, "boleto");

        return Response.noContent().build();
    }

    @PATCH
    @Path("{pedido}/pix/{pix}")
    @RolesAllowed({"User"})
    public Response pixPayment(@PathParam("pix") Long pix, @PathParam("pedido") Long pedido) {
        String username = jwt.getSubject();

        pagamentoService.payment(pedido, pix, username, "pix");

        return Response.noContent().build();
    }
}
