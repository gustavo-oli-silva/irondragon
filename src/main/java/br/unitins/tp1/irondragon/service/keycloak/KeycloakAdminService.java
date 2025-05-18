package br.unitins.tp1.irondragon.service.keycloak;

import br.unitins.tp1.irondragon.dto.request.keycloak.KeycloakUserRequestDTO;

public interface KeycloakAdminService {

    void createKeycloakUser(KeycloakUserRequestDTO dto);


    String getAdminAccessToken();

    String getUserIdByUsername(String username);

    void assignRealmRoleToUser(String userId, String roleName);

    void updateNameUser(String userId, String name);
}
