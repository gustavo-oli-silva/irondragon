package br.unitins.tp1.irondragon.dto.response.keycloak;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponseDTO(

        @JsonProperty("access_token") String accessToken) {

}
