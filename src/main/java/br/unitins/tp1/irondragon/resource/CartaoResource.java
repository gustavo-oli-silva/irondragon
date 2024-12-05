package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.CartaoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.CartaoResponseDTO;
import br.unitins.tp1.irondragon.service.cartao.CartaoService;
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
@Path("/cartoes")
public class CartaoResource {
    private static final Logger LOGGER = Logger.getLogger(CartaoResource.class);

    @Inject
    public CartaoService cartaoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado com o parametro [" + id + "] !");

        return Response.ok(CartaoResponseDTO.valueOf(cartaoService.findById(id))).build();
    }

    @GET
    @RolesAllowed({"Super", "Admin"})
    public Response findAll() {
        LOGGER.info("Método findAll foi executado!");

        return Response.ok(cartaoService.findAll().stream().map(CartaoResponseDTO::valueOf).toList()).build();
    }

    @POST
    @RolesAllowed({"User"})
    public Response create(@Valid CartaoRequestDTO cartao) {
        String username = jwt.getSubject();

        LOGGER.info("Cliente [" + username + "] cadastrou o cartao: " + cartao);

        return Response
                .status(Response.Status.CREATED)
                .entity(CartaoResponseDTO.valueOf(cartaoService.create(cartao, username)))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"User"})
    public Response update(@PathParam("id") Long id, @Valid CartaoRequestDTO cartao) {
        String username = jwt.getSubject();

        LOGGER.info("Cliente [" + username + "] modificou o Cartão " + id + ": " + cartao);

        cartaoService.update(id, cartao, username);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"User"})
    public Response delete(@PathParam("id") Long idCartao) {
        String username = jwt.getSubject();

        LOGGER.info("Cliente [" + username + "] deletou o cartão " + idCartao);

        cartaoService.delete(idCartao, username);
        return Response.noContent().build();
    }

    @GET
    @Path("/usuarios")
    @RolesAllowed({"User"})
    public Response listByUsername() {
        String username = jwt.getSubject();

        LOGGER.info("Cliente [" + username + "] pediu a lista dos cartões cadastrados");

        return Response.ok(cartaoService.listByUsername(username)).build();
    }
}
