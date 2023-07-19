package com.covoiturage.user.query.controllers;
import com.covoiturage.user.query.entities.Account;
import com.covoiturage.user.query.repositories.AccountRepository;
import com.covoiturage.user.query.services.KeycloakAdminClientService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/key")

@CrossOrigin(origins = "*")
public class  AccountQueryController {
    private final KeycloakAdminClientService kcAdminClient;
    private  final AccountRepository accountRepository;

    public AccountQueryController(KeycloakAdminClientService kcAdminClient,  AccountRepository accountRepository) {
        this.kcAdminClient = kcAdminClient;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserRepresentation> getUserById(@PathVariable String userId) {
        org.keycloak.representations.idm.UserRepresentation userRepresentation = kcAdminClient.getUserById(userId);
        if (userRepresentation == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userRepresentation);
        }
    }
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Optional<Account>> showAccountById(@PathVariable String id, Model model) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
        } else {
            throw new RuntimeException("Account not found with ID: " + id);
        }
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRepresentation> getUserByIdd(@PathVariable("id") String id) {
        UserRepresentation user = kcAdminClient.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/users/me")
    public ResponseEntity<Account> getCurrentUser(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) keycloakAuthenticationToken.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = keycloakPrincipal.getKeycloakSecurityContext();
        String userId = keycloakSecurityContext.getToken().getSubject();

        org.keycloak.representations.idm.UserRepresentation userRepresentation = kcAdminClient.getUserById(userId);
        if (userRepresentation == null) {
            return ResponseEntity.notFound().build();
        } else {
            Account account = accountRepository.findByUsername(userRepresentation.getUsername());
            if (account == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(account);
            }
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<Account>> getAllUsers() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(accounts);
        }
    }


}