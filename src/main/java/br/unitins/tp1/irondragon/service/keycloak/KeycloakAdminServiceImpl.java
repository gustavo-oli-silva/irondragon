package br.unitins.tp1.irondragon.service.keycloak;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.jose4j.json.internal.json_simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.unitins.tp1.irondragon.dto.request.keycloak.KeycloakUserRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

    @Override
    public String getAdminAccessToken() {
        String url = "http://localhost:8180/realms/quarkus/protocol/openid-connect/token";
        String form = "username=admin&password=admin&grant_type=password&client_id=backend-service&client_secret=secret";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica o status da resposta HTTP
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree(response.body());
                String token = json.get("access_token").asText();

                System.out.println("Access Token: " + token);
                return token;
            } else {
                System.err.println("Erro ao obter o token. Status Code: " + response.statusCode());
                return null;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createKeycloakUser(KeycloakUserRequestDTO dto) {
        String realm = "quarkus";
        String accessToken = getAdminAccessToken();

        // Converte o DTO em JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody;
        try {
            jsonBody = mapper.writeValueAsString(dto);
            System.out.println(jsonBody);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter DTO para JSON", e);
        }

        String url = "http://localhost:8180/admin/realms/" + realm + "/users";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .POST(BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());
            System.out.println("Resposta: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserIdByUsername(String username) {
        String realm = "quarkus"; // Defina o nome do realm conforme necessário
        String accessToken = getAdminAccessToken(); // Obtém o token de acesso

        String url = "http://localhost:8180/admin/realms/" + realm + "/users?username="
                + URLEncoder.encode(username, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(response.body());
                if (jsonResponse.isArray() && jsonResponse.size() > 0) {
                    return jsonResponse.get(0).get("id").asText(); // Retorna o ID do usuário
                } else {
                    throw new RuntimeException("Usuário não encontrado no Keycloak: " + username);
                }
            } else {
                throw new RuntimeException("Erro ao buscar usuário: Status Code " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao processar a resposta da requisição", e);
        }
    }

    @Override
    public void assignRealmRoleToUser(String userId, String roleName) {
        String realm = "quarkus"; // Defina o nome do realm conforme necessário
        String accessToken = getAdminAccessToken(); // Obtém o token de acesso

        // URL para buscar a role
        String roleUrl = "http://localhost:8180/admin/realms/" + realm + "/roles/"
                + URLEncoder.encode(roleName, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest roleRequest = HttpRequest.newBuilder()
                .uri(URI.create(roleUrl))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> roleResponse = client.send(roleRequest, HttpResponse.BodyHandlers.ofString());

            if (roleResponse.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode roleJson = mapper.readTree(roleResponse.body());
                if (roleJson.isObject()) {
                    // Agora, fazer o POST com a role no userId
                    String assignRoleUrl = "http://localhost:8180/admin/realms/" + realm + "/users/" + userId
                            + "/role-mappings/realm";

                    HttpRequest assignRoleRequest = HttpRequest.newBuilder()
                            .uri(URI.create(assignRoleUrl))
                            .header("Authorization", "Bearer " + accessToken)
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString("[" + roleJson.toString() + "]"))
                            .build();

                    HttpResponse<String> assignRoleResponse = client.send(assignRoleRequest,
                            HttpResponse.BodyHandlers.ofString());

                    if (assignRoleResponse.statusCode() != 204) {
                        throw new RuntimeException(
                                "Erro ao atribuir a role ao usuário: " + roleName + " para o usuário " + userId);
                    }
                } else {
                    throw new RuntimeException("Role não encontrada: " + roleName);
                }
            } else {
                throw new RuntimeException("Erro ao buscar a role: Status Code " + roleResponse.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao processar a requisição ou resposta", e);
        }
    }

}
