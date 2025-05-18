package br.unitins.tp1.irondragon.service.keycloak;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.unitins.tp1.irondragon.dto.request.keycloak.KeycloakUserRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

    private static final String BASE_URL = "http://localhost:8180";
    private static final String REALM = "quarkus";
    private static final String CLIENT_ID = "backend-service";
    private static final String CLIENT_SECRET = "secret";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getAdminAccessToken() {
        String url = BASE_URL + "/realms/" + REALM + "/protocol/openid-connect/token";
        String form = String.format(
                "username=%s&password=%s&grant_type=password&client_id=%s&client_secret=%s",
                ADMIN_USERNAME, ADMIN_PASSWORD, CLIENT_ID, CLIENT_SECRET);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        return sendRequest(request, 200, "access_token");
    }

    @Override
    public void createKeycloakUser(KeycloakUserRequestDTO dto) {
        String url = BASE_URL + "/admin/realms/" + REALM + "/users";
        String accessToken = getAdminAccessToken();

        try {
            String jsonBody = mapper.writeValueAsString(dto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            sendVoidRequest(request, 201);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter DTO para JSON", e);
        }
    }

    @Override
    public String getUserIdByUsername(String username) {
        String accessToken = getAdminAccessToken();
        String url = BASE_URL + "/admin/realms/" + REALM + "/users?username=" +
                URLEncoder.encode(username, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonNode users = mapper.readTree(response.body());
                if (users.isArray() && !users.isEmpty()) {
                    return users.get(0).get("id").asText();
                }
                throw new RuntimeException("Usuário não encontrado: " + username);
            }
            throw new RuntimeException("Erro ao buscar usuário: Status " + response.statusCode());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao processar resposta da requisição", e);
        }
    }

    @Override
    public void assignRealmRoleToUser(String userId, String roleName) {
        String accessToken = getAdminAccessToken();
        String roleUrl = BASE_URL + "/admin/realms/" + REALM + "/roles/" +
                URLEncoder.encode(roleName, StandardCharsets.UTF_8);

        HttpRequest roleRequest = HttpRequest.newBuilder()
                .uri(URI.create(roleUrl))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> roleResponse = httpClient.send(roleRequest, HttpResponse.BodyHandlers.ofString());

            if (roleResponse.statusCode() == 200) {
                JsonNode roleJson = mapper.readTree(roleResponse.body());

                String assignUrl = BASE_URL + "/admin/realms/" + REALM + "/users/" + userId + "/role-mappings/realm";
                HttpRequest assignRequest = HttpRequest.newBuilder()
                        .uri(URI.create(assignUrl))
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString("[" + roleJson.toString() + "]"))
                        .build();

                sendVoidRequest(assignRequest, 204);

            } else {
                throw new RuntimeException("Erro ao buscar role: Status " + roleResponse.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao atribuir role ao usuário", e);
        }
    }

    private String sendRequest(HttpRequest request, int expectedStatus, String fieldToExtract) {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == expectedStatus) {
                JsonNode json = mapper.readTree(response.body());
                return json.get(fieldToExtract).asText();
            }
            throw new RuntimeException("Falha na requisição: Status " + response.statusCode());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao executar requisição HTTP", e);
        }
    }

    private void sendVoidRequest(HttpRequest request, int expectedStatus) {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != expectedStatus) {
                throw new RuntimeException("Falha na requisição: Status " + response.statusCode() +
                        "\nResposta: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao executar requisição HTTP", e);
        }
    }

    @Override
    public void updateNameUser(String userId, String name) {
        String accessToken = getAdminAccessToken();
        String url = BASE_URL + "/admin/realms/" + REALM + "/users/" + userId;

    
        String jsonBody = String.format("{\"firstName\": \"%s\"}", name);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        sendVoidRequest(request, 204); 
    }

}
