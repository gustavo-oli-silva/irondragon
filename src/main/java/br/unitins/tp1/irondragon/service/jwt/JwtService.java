package br.unitins.tp1.irondragon.service.jwt;

import br.unitins.tp1.irondragon.dto.response.usuario.UsuarioResponseDTO;

public interface JwtService {
    public String generateJwt(UsuarioResponseDTO dto);
}
