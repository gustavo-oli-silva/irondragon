package br.unitins.tp1.irondragon.service.jwt;

import br.unitins.tp1.irondragon.dto.response.usuario.UsuarioResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {
    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UsuarioResponseDTO dto) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<>();
        roles.add(dto.perfil().getLabel());

        return Jwt.issuer("unitins-jwt")
                .subject(dto.username())
                .groups(roles)
                .expiresAt(expiryDate)
                .sign();
    }
}