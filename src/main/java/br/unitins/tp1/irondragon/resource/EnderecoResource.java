package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.EnderecoResponseDTO;
import br.unitins.tp1.irondragon.service.endereco.EnderecoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/enderecos")
public class EnderecoResource {
    private static final Logger LOGGER = Logger.getLogger(EnderecoResource.class);

    @Inject
    public EnderecoService enderecoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @RolesAllowed({"Super", "Admin"})
    public Response findAll() {
        LOGGER.info("Método findAll foi executado!");

        return Response
                .ok(enderecoService.findAll().stream().map(EnderecoResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    @Path("/usuarios")
    @RolesAllowed({"User", "Admin"})
    public Response listByUsername() {
        String username = jwt.getSubject();

        LOGGER.info("Cliente [" + username + "] pediu uma lista dos seus endereços!");

        return Response
                .ok(enderecoService.listByUsername(username).stream().map(EnderecoResponseDTO::valueOf).toList())
                .build();
    }

    @POST
    @RolesAllowed({"User", "Admin"})
    public Response create(EnderecoRequestDTO dto) {
        String username = jwt.getSubject();

        LOGGER.info("Cliente [" + username + "] cadastrou um endereço: " + dto);

        return Response.ok(EnderecoResponseDTO.valueOf(enderecoService.create(dto, username))).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response update(@PathParam("id") Long id, EnderecoRequestDTO dto) {
        String username = jwt.getSubject();

        LOGGER.info("Cliente [" + username + "] modificou o endereço " + id + ": " + dto);

        enderecoService.update(id, dto, username);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        String username = jwt.getSubject();

        LOGGER.info("Cliente [" + username + "] deletou o endereço " + id);

        enderecoService.delete(id, username);
        return Response.noContent().build();
    }
}
