package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.CartaoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.CartaoResponseDTO;
import br.unitins.tp1.irondragon.service.cartao.CartaoService;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/cartoes")
public class CartaoResource {
    @Inject
    public CartaoService cartaoService;

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(CartaoResponseDTO.valueOf(cartaoService.findById(id))).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(cartaoService.findAll().stream().map(CartaoResponseDTO::valueOf).toList()).build();
    }

    @POST
    public Response create(@Valid CartaoRequestDTO cartao) {
        String username = jwt.getSubject();

        return Response
                .status(Response.Status.CREATED)
                .entity(CartaoResponseDTO.valueOf(cartaoService.create(username, cartao)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CartaoRequestDTO cartao) {
        cartaoService.update(id, cartao);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long idCartao) {
        cartaoService.delete(idCartao);
        return Response.noContent().build();
    }
}
