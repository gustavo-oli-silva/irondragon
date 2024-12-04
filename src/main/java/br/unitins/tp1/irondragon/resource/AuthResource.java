package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.auth.AuthRequestDTO;
import br.unitins.tp1.irondragon.dto.request.auth.FuncionarioAuthRequestDTO;
import br.unitins.tp1.irondragon.dto.response.usuario.UsuarioResponseDTO;
import br.unitins.tp1.irondragon.model.usuario.Funcionario;
import br.unitins.tp1.irondragon.model.usuario.Perfil;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.service.funcionario.FuncionarioService;
import br.unitins.tp1.irondragon.service.hash.HashService;
import br.unitins.tp1.irondragon.service.jwt.JwtService;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private static final Logger LOGGER = Logger.getLogger(AuthResource.class);

    @Inject
    public HashService hashService;

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public FuncionarioService funcionarioService;

    @Inject
    public JwtService jwtService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response usuarioLogin(AuthRequestDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        Usuario usuario = usuarioService.findByUsernameAndSenha(authDTO.username(), hash);

        if (usuario == null) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Usuario não encontrado").build();
        }

        if(usuario.getPerfil() == Perfil.SUPER) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Usuario não encontrado").build();
        }

        usuarioService.changeProfile(usuario, Perfil.USER);

        LOGGER.info("Cliente " + usuario.getUsername() + " logou no sistema!");

        return Response.ok()
                .header("Authorization", jwtService.generateJwt(UsuarioResponseDTO.valueOf(usuario)))
                .build();

    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/admin")
    public Response funcionarioLogin(FuncionarioAuthRequestDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        Funcionario funcionario = funcionarioService.findByCpfAndSenha(authDTO.cpf(), hash);

        if(funcionario == null) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("Funcionário inválido!")
                    .build();
        }

        if(funcionario.getUsuario().getPerfil() != Perfil.SUPER) {
            usuarioService.changeProfile(funcionario.getUsuario(), Perfil.ADMIN);
        }

        LOGGER.info("Funcionário [" + funcionario.getUsuario().getUsername() + "] fez login");

        return Response.ok()
                .header("Authorization", jwtService.generateJwt(UsuarioResponseDTO.valueOf(funcionario.getUsuario())))
                .build();
    }
}
