package br.unitins.tp1.irondragon.resource.usuario;

import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.dto.response.usuario.UsuarioResponseDTO;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/usuarios")
public class UsuarioResource {
    @Inject
    public UsuarioService usuarioService;

    @Inject
    public JsonWebToken jwt;

    @GET
    public Response findAll() {
        return Response.ok(usuarioService.findAll().stream().map(UsuarioResponseDTO::valueOf)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findById(id))).build();
    }

    @POST
    public Response create(@Valid UsuarioRequestDTO dto) {
        return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.create(dto))).build();
    }

    @GET
    @Path("/meu-perfil")
    @RolesAllowed({"User", "Admin"})
    public Response showProfile() {
        String username = jwt.getSubject();

        return Response.ok(usuarioService.findByUsername(username)).build();
    }
}
