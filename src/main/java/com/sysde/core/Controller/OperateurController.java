package com.sysde.core.Controller;

import com.sysde.core.entity.OperateurEntity;
import com.sysde.core.service.OperateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operateur")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Operateur", description = "Ensemble des entreprises agréées intervenant dans la gestion des activités techniques et commerciales de la filière hévéa.")
public class OperateurController {

    private final OperateurService operateurService;


    /**
     * Récupère un opérateur par son identifiant
     *
     * @param operateurID Identifiant unique de l'opérateur.
     * @return l'entité contenant les informations complètes de l'opérateur.
     */
    @Operation(summary = "Détails d'un opérateur par ID",
            description = "Retourne l'ensemble des informations d'un opérateur (Raison sociale, Acronyme, etc) à partir de son identifiant unique.")
    @ApiResponse(responseCode = "200", description = "Opérateur trouvé et retourné avec succès")
    @ApiResponse(responseCode = "404", description = "Aucun opérateur trouvé pour l'ID fourni")
    @GetMapping (value = "/{operateurID}")
    public ResponseEntity<OperateurEntity> getOperateurByID(
            @Parameter(description = "Identifiant unique de l'opérateur", example = "2")
            @PathVariable Long operateurID) {

        OperateurEntity operateur = operateurService.findOperateurByID(operateurID);
        return ResponseEntity.ok(operateur);
    }



    /**
     * Récupère la liste filtrée des opérateurs encadreurs.
     *
     * @return une liste (éventuellement vide) des opérateurs encadreurs.
     */
    @Operation(summary = "Lister les opérateurs encadreurs",
            description = "Retourne tous les opérateurs dont le statut d'encadreur est à 'True'.")
    @ApiResponse(responseCode = "200", description = "Liste récupérée (peut être vide si aucun encadreur n'existe)")
    @GetMapping (value = "/encadreurs")
    public ResponseEntity<List<OperateurEntity>> getOperateurEncadreurs() {

        List<OperateurEntity> operateurs = operateurService.findByOperateurEncadreur();
        return ResponseEntity.ok(operateurs); // Retourne [] et 200 OK
    }



    /**
     * Récupère la liste filtrée des opérateurs non encadreurs.
     *
     * @return une liste (éventuellement vide) des opérateurs non encadreurs.
     */
    @Operation(summary = "Lister les opérateurs non encadreurs",
            description = "Retourne tous les opérateurs dont le statut d'encadreur est à 'false'.")
    @ApiResponse(responseCode = "200", description = "Liste des opérateurs non-encadreurs récupérée avec succès")
    @ApiResponse(responseCode = "204", description = "Aucun opérateur non-encadreur trouvé")
    @GetMapping (value = "/non-encadreurs")
    public ResponseEntity<List<OperateurEntity>> getOperateurNonEncadrreurs() {
        List<OperateurEntity> operateurs = operateurService.findByOperateurNonEncadreur();
        if (operateurs == null || operateurs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(operateurs);
    }



    /**
     * Récupère l'intégralité des opérateurs de la base de données.
     *
     * @return la liste de tous les opérateurs ou, un statut 204 si la base est vide.
     */
    @Operation(summary = "Lister tous les opérateurs",
            description = "Retourne l'intégralité des opérateurs. Renvoie un code 204 si aucun opérateur n'est présent.")
    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès")
    @ApiResponse(responseCode = "204", description = "Aucun opérateur trouvé en base de données")
    @GetMapping (value = "/all")
    public ResponseEntity<List<OperateurEntity>> getOperateurs() {
        List<OperateurEntity> operateurs = operateurService.findByOperateurs();
        if (operateurs.isEmpty() || operateurs == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(operateurs);
    }



    /**
     * Enregistre un nouvel opérateur dans le système.
     *
     * @param operateur L'entité contenant les informations de l'opérateur à créer.
     * @return l'objet créé ou un statut d'erreur.
     */
    @Operation(summary = "Créer un nouvel opérateur",
            description = "Enregistre un opérateur en base de données et retourne l'objet créé.")
    @ApiResponse(responseCode = "201", description = "Opérateur créé avec succès")
    @ApiResponse(responseCode = "400", description = "Données d'enttrées invalides ou erreur de syntaxe")
    @ApiResponse(responseCode = "500", description = "Erreur technique lors de l'enregistrement")
    @PostMapping (value = "/")
    public ResponseEntity<OperateurEntity> saveOperateur(@RequestBody OperateurEntity operateur) {
        try {
            // Logique de sauvegarde
            OperateurEntity nouveauOperateur = operateurService.saveOperateur(operateur);
            return new ResponseEntity<>(nouveauOperateur, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Cas où les données envoyées sont incorrectes (ex: nom vide)
            return ResponseEntity.badRequest().build(); // Retourne 400

        } catch (Exception e) {
            // Cas d'une erreur serveur imprévue
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retourne 500
        }
    }



    /**
     * Met à jour les informations d'un opérateur existant.
     *
     * @param operateurID Identifiant de l'opérateur à modifier.
     * @param operateurDetails Nouvelles données à appliquer.
     * @return l'opérateur mis à jour ou une erreur 404.
     */
    @Operation(summary = "Modifier un opérateur",
            description = "Met à jour un opérateur existant à partir de son ID. Retourne 404 si l'ID est inconnu.")
    @ApiResponse(responseCode = "200", description = "Mise à jour effectuée")
    @ApiResponse(responseCode = "404", description = "Opérateur introuvable")
    @ApiResponse(responseCode = "500", description = "Erreur lors de la mise à jour")
    @PutMapping("/{operateurID}")
    public ResponseEntity<OperateurEntity> updateOperateur(
            @PathVariable Long operateurID,
            @RequestBody OperateurEntity operateurDetails) {

        try {
            // 1. Vérifier l'existence
            OperateurEntity operateurExistant = operateurService.findOperateurByID(operateurID);
            if (operateurExistant == null) {
                return ResponseEntity.notFound().build(); // Retourne 404
            }

            // 2. Mise à jour (le service gère généralement l'injection de l'ID)
            operateurDetails.setOperateurID(operateurID);
            OperateurEntity updated = operateurService.saveOperateur(operateurDetails);

            return ResponseEntity.ok(updated); // Retourne 200 avec l'objet modifié

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
