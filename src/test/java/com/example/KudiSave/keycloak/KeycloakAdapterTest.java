package com.example.KudiSave.keycloak;

import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.output.persistence.adapters.keycloak.KeycloakAdapter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
@SpringBootTest
class KeycloakAdapterTest {

   @Autowired
   private KeycloakAdapter keycloakAdapter;

   private KudiUser appUser;

   @BeforeEach
   void setUp() {
        appUser = new KudiUser();
       appUser.setFirstname("Jameson");
       appUser.setLastname("Peters");
       appUser.setPassword("password");
       appUser.setEmail("jamesonpeters@gmail.com");
       appUser.setPhoneNumber("09262280695");
       appUser.setUsername("jamie");

   }

    @Test
    void test_CreateUser() {
            KudiUser createdKeycloakUser = keycloakAdapter.createUser(appUser);
            assertNotNull(createdKeycloakUser);
            log.info("Created keycloak user ==>> {}",createdKeycloakUser);
            keycloakAdapter.deleteUser(createdKeycloakUser);
    }

    @Test
    void test_FindKeycloakUser() {
            KudiUser createdKeycloakUser = keycloakAdapter.createUser(appUser);
            assertNotNull(createdKeycloakUser);
            UserRepresentation foundUser =keycloakAdapter.findKeycloakUserByEmail(createdKeycloakUser.getEmail());
            assertNotNull(foundUser);
            keycloakAdapter.deleteUser(createdKeycloakUser);

    }


}