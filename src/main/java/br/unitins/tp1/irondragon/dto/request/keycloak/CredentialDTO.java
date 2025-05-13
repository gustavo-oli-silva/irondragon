package br.unitins.tp1.irondragon.dto.request.keycloak;

public record CredentialDTO(
        String type,
        String value,
        boolean temporary) {
}
