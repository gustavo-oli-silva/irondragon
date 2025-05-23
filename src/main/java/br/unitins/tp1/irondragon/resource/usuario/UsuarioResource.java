    package br.unitins.tp1.irondragon.resource.usuario;

    import java.io.IOException;

    import org.eclipse.microprofile.jwt.JsonWebToken;
    import org.jboss.logging.Logger;
    import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

    import br.unitins.tp1.irondragon.dto.request.usuario.EmailUpdateDTO;
    import br.unitins.tp1.irondragon.dto.request.usuario.SenhaUpdateDTO;
    import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioUpdateBasicoDTO;
import br.unitins.tp1.irondragon.dto.response.usuario.UsuarioResponseDTO;
    import br.unitins.tp1.irondragon.form.ProcessadorImageForm;
    import br.unitins.tp1.irondragon.service.file.UsuarioFileServiceImpl;
    import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
    import jakarta.annotation.security.PermitAll;
    import jakarta.annotation.security.RolesAllowed;
    import jakarta.inject.Inject;
    import jakarta.validation.Valid;
    import jakarta.ws.rs.Consumes;
    import jakarta.ws.rs.DELETE;
    import jakarta.ws.rs.GET;
    import jakarta.ws.rs.PATCH;
    import jakarta.ws.rs.POST;
    import jakarta.ws.rs.Path;
    import jakarta.ws.rs.PathParam;
    import jakarta.ws.rs.Produces;
    import jakarta.ws.rs.core.MediaType;
    import jakarta.ws.rs.core.Response;

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
        @Path("/search/{username}")
        public Response findByUsername(@PathParam("username") String username) {
            LOGGER.info("Método findByUsername foi executado com o parametro [ " + username + "] !");

            return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findByUsername(username))).build();
        }

        @GET
        @Path("/search/cpf/{cpf}")
        public Response findByCpf(@PathParam("cpf") String cpf) {
            LOGGER.info("Método findByUsername foi executado com o parametro [ " + cpf + "] !");

            return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findByCpf(cpf))).build();
        }

        @GET
        @Path("/{id}")
        @RolesAllowed({"Super", "Admin"})
        public Response findById(@PathParam("id") Long id) {
            LOGGER.info("Método findById foi executado com o parametro [" + id + "] !");

            return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findById(id))).build();
        }

        @POST
        @PermitAll
        public Response create(@Valid UsuarioRequestDTO dto) {
            LOGGER.info("Método create foi executado, Usuario: " + dto);

            return Response.status(Response.Status.CREATED).entity(UsuarioResponseDTO.valueOf(usuarioService.create(dto))).build();
        }

        @PATCH
        @Path("/email")
        @RolesAllowed({"Super", "Admin", "User"})
        public Response updateEmail(@Valid EmailUpdateDTO dto) {
            String username = jwt.getClaim("preferred_username");

            LOGGER.info("O cliente " + username + " pediu uma troca de email, email: " + dto.email());

            usuarioService.updateEmail(dto, username);
            return Response.noContent().build();
        }

        @PATCH
        @Path("/senha")
        @RolesAllowed({"Super", "Admin", "User"})
        public Response updateSenha(@Valid SenhaUpdateDTO dto) {
            String username = jwt.getClaim("preferred_username");

            LOGGER.info("O cliente " + username + " pediu uma troca de email, email: " + dto.senha());

            usuarioService.updateSenha(dto, username);
            return Response.noContent().build();
        }

        @PATCH
        @Path("/info-basica")
        @RolesAllowed({"Super", "Admin", "User"})
        public Response updateInfoBasicas(@Valid UsuarioUpdateBasicoDTO dto) {
            String username = jwt.getClaim("preferred_username");

            LOGGER.info("O cliente " + username + " pediu uma troca de de informações básicas, novos dados: " + dto.toString());

            usuarioService.updateInfoBasicas(username, dto);
            return Response.noContent().build();
        }

        @GET
        @Path("/meu-perfil")
        @RolesAllowed({"User", "Admin"})
        public Response showProfile() {
            String username = jwt.getClaim("preferred_username");

            LOGGER.info("O Cliente " + username + " pediu os dados do seu perfil!");

            return Response.ok(usuarioService.findByUsername(username)).build();
        }

        @PATCH
        @Path("{id}/upload/imagem")
        @RolesAllowed({"Super", "Admin", "User"})
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        public Response uploadImage(@PathParam("id") Long id, @MultipartForm ProcessadorImageForm form) {
            String username = jwt.getClaim("preferred_username");

            LOGGER.info("Método uploadImage foi executado");

            try {
                String nomeImagem = usuarioFileService.save(id,form.getNomeImagem(), form.getImagem());
                usuarioService.updateNomeImagem(username, nomeImagem);
            } catch (IOException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            return Response.noContent().build();
        }

        @GET
        @Path("{id}/download/imagem/{nomeImagem}")
        @Produces(MediaType.APPLICATION_OCTET_STREAM)
        public Response downloadImage(@PathParam("id") Long id, @PathParam("nomeImagem") String nomeImagem) {
            LOGGER.info("Método downloadImage foi executado");

            Response.ResponseBuilder response = Response.ok(usuarioFileService.find(id, nomeImagem));
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
