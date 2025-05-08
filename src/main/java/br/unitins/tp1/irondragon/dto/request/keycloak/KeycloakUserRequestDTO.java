package br.unitins.tp1.irondragon.dto.request.keycloak;

import java.util.List;

public record KeycloakUserRequestDTO(
    String username,
    String email,
    boolean enabled,
    List<CredentialDTO> credentials
) {
   
}
