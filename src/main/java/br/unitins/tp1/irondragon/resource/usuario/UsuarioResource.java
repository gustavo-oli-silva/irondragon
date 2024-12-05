package br.unitins.tp1.irondragon.resource.usuario;

import br.unitins.tp1.irondragon.dto.request.usuario.EmailUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.SenhaUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.dto.response.usuario.UsuarioResponseDTO;
import br.unitins.tp1.irondragon.form.ProcessadorImageForm;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.service.file.UsuarioFileServiceImpl;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.IOException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/usuarios")
public class UsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class);

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public UsuarioFileServiceImpl usuarioFileService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @RolesAllowed({"Super", "Admin"})
    public Response findAll() {
        LOGGER.info("Método findAll foi executado!");

        return Response.ok(usuarioService.findAll().stream().map(UsuarioResponseDTO::valueOf)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado com o parametro [" + id + "] !");

        return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findById(id))).build();
    }

    @POST
    public Response create(@Valid UsuarioRequestDTO dto) {
        LOGGER.info("Método create foi executado, Usuario: " + dto);

        return Response.status(Response.Status.CREATED).entity(UsuarioResponseDTO.valueOf(usuarioService.create(dto))).build();
    }

    @PATCH
    @Path("/email")
    @RolesAllowed({"Super", "Admin", "User"})
    public Response updateEmail(@Valid EmailUpdateDTO dto) {
        String username = jwt.getSubject();

        LOGGER.info("O cliente " + username + " pediu uma troca de email, email: " + dto.email());

        usuarioService.updateEmail(dto, username);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/senha")
    @RolesAllowed({"Super", "Admin", "User"})
    public Response updateSenha(@Valid SenhaUpdateDTO dto) {
        String username = jwt.getSubject();

        LOGGER.info("O cliente " + username + " pediu uma troca de email, email: " + dto.senha());

        usuarioService.updateSenha(dto, username);
        return Response.noContent().build();
    }

    @GET
    @Path("/meu-perfil")
    @RolesAllowed({"User", "Admin"})
    public Response showProfile() {
        String username = jwt.getSubject();

        LOGGER.info("O Cliente " + username + " pediu os dados do seu perfil!");

        return Response.ok(usuarioService.findByUsername(username)).build();
    }

    @PATCH
    @Path("/upload/imagem")
    @RolesAllowed({"Super", "Admin", "User"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@MultipartForm ProcessadorImageForm form) {
        String username = jwt.getSubject();

        LOGGER.info("Método uploadImage foi executado");

        try {
            String nomeImagem = usuarioFileService.save(form.getNomeImagem(), form.getImagem());
            usuarioService.updateNomeImagem(username, nomeImagem);
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        LOGGER.info("Método downloadImage foi executado");

        Response.ResponseBuilder response = Response.ok(usuarioFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("O cliente " + id + " está em processo de ser deletado!");

        usuarioService.delete(id);

        return Response.noContent().build();
    }
}
