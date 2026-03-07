package com.sysde.core.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Core - SYSDE",
                version = "1.0",
                description = "### Système de Gestion des Opérateurs\n" +
                        "Cette API constitue le module central du système **SYSDE**, dédié à la gestion référentielle des opérateurs.\n\n" +
                        "**Fonctionnalités clés :**\n" +
                        "*   **Cycle de vie complet (CRUD)** des opérateurs.\n" +
                        "*   **Filtrage métier** par statut d'encadrement (Encadreurs / Non-Encadreurs).\n" +
                        "*   **Gestion unitaire** précise par identifiant unique.\n\n" +
                        "**Standards de réponse :**\n" +
                        "*   `200/201` : Succès de l'opération.\n" +
                        "*   `204` : Aucune donnée trouvée (Liste vide).\n" +
                        "*   `404` : Identifiant inconnu.\n\n",
                contact = @Contact(
                        name = ": Équipe Développement SYSDE",
                        email = "abraham.tiene@apromac.ci"
                )
        )
)
public class OpenApiConfig {

}
