package br.unitins.tp1.irondragon.resource.usuario;

import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.dto.response.usuario.UsuarioResponseDTO;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/usuarios")
public class UsuarioResource {
    @Inject
    public UsuarioService usuarioService;

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
}
