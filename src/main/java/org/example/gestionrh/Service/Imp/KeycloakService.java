package org.example.gestionrh.Service.Imp;

import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakService {

    @Value("${keycloak.realm}")
    private String realm;

    @Autowired
    private Keycloak keycloakAdmin;



    public String createUserWithRole(String username, String email, String password, String roleName) {
        // 1. Créer l'utilisateur
        String userId="";
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setFirstName(username);
        user.setLastName(username);
        user.setEmail(email);
        user.setEnabled(true);

        try (Response response = keycloakAdmin.realm(realm).users().create(user)) {
            if (response.getStatus() != 201) {
                throw new RuntimeException("Erreur lors de la création de l'utilisateur");
            }

            userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            // 2. Définir le mot de passe
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(password);
            keycloakAdmin.realm(realm).users().get(userId).resetPassword(passwordCred);

            // 3. Assigner le rôle
            RoleRepresentation role = keycloakAdmin.realm(realm).roles().get(roleName).toRepresentation();
            keycloakAdmin.realm(realm).users().get(userId).roles().realmLevel().add(List.of(role));
        }

        return userId;
    }

    public void updateUserInfo(String userId, String newUsername, String newEmail) {

        UserResource userResource = keycloakAdmin.realm(realm).users().get(userId);
        UserRepresentation user = userResource.toRepresentation();

        user.setUsername(newUsername);
        user.setFirstName(newUsername);
        user.setLastName(newUsername);
        user.setEmail(newEmail);

        userResource.update(user);

    }


    public void deleteUser(String userId) {
        UserResource userResource = keycloakAdmin.realm(realm).users().get(userId);

        // Vérifie si l'utilisateur existe (sinon .remove() peut lever une erreur 404)
        if (userResource.toRepresentation() != null) {
            userResource.remove();
        } else {
            throw new RuntimeException("Utilisateur avec l'ID " + userId + " non trouvé.");
        }
    }


}
