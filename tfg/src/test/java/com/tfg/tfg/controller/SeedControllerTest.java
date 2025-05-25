package com.tfg.tfg.controller;

import com.tfg.tfg.dto.SeedInPutDTO;
import com.tfg.tfg.dto.SeedOutPutDTO;
import com.tfg.tfg.entities.Seed;
import com.tfg.tfg.entities.User;
import com.tfg.tfg.services.SeedService;
import com.tfg.tfg.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SeedControllerTest {

    @Mock private SeedService seedService;
    @Mock private UserService userService;
    @Mock private Authentication auth;
    @InjectMocks private SeedController controller;

    @Test
    @DisplayName("Given an authenticated user with seeds, when list is called, then returns all SeedOutPutDTOs")
    void givenAuthenticatedUserWithSeeds_whenList_thenReturnsDtos() {
        // make the mock Authenticated!
        given(auth.isAuthenticated()).willReturn(true);
        given(auth.getName()).willReturn("user1");
        User u = User.builder().id(42L).name("user1").build();
        given(userService.findByName("user1")).willReturn(u);

        Seed seed1 = mock(Seed.class), seed2 = mock(Seed.class);
        given(seed1.getId()).willReturn(1L);
        given(seed1.getSeedValue()).willReturn("AAA");
        given(seed1.getName()).willReturn("First");
        given(seed2.getId()).willReturn(2L);
        given(seed2.getSeedValue()).willReturn("BBB");
        given(seed2.getName()).willReturn("Second");
        given(seedService.findByUser(42L)).willReturn(List.of(seed1, seed2));

        ResponseEntity<List<SeedOutPutDTO>> resp = controller.list(auth);

        assertThat(resp.getStatusCodeValue()).isEqualTo(200);
        List<SeedOutPutDTO> body = resp.getBody();
        assertThat(body).hasSize(2);

        // use the Java property names
        assertThat(body).extracting("id").containsExactly(1L, 2L);
        assertThat(body).extracting("seedValue").containsExactly("AAA", "BBB");
        assertThat(body).extracting("name").containsExactly("First", "Second");

        then(userService).should().findByName("user1");
        then(seedService).should().findByUser(42L);
    }


    @Test
    @DisplayName("Given valid input, when add is called, then saves seed and returns DTO with 201")
    void givenValidInput_whenAdd_thenSavesAndReturnsCreatedDto() {
        given(auth.isAuthenticated()).willReturn(true);
        given(auth.getName()).willReturn("userX");
        User u = User.builder().id(7L).name("userX").build();
        given(userService.findByName("userX")).willReturn(u);

        SeedInPutDTO input = SeedInPutDTO.builder()
                .seedValue("XYZ")
                .name("MySeed")
                .build();

        Seed saved = mock(Seed.class);
        given(saved.getId()).willReturn(99L);
        given(saved.getSeedValue()).willReturn("XYZ");
        given(saved.getName()).willReturn("MySeed");
        given(seedService.save(7L, "XYZ", "MySeed")).willReturn(saved);

        ResponseEntity<SeedOutPutDTO> resp = controller.add(auth, input);

        assertThat(resp.getStatusCodeValue()).isEqualTo(201);
        var dto = resp.getBody();
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(99L);
        assertThat(dto.getSeedValue()).isEqualTo("XYZ");
        assertThat(dto.getName()).isEqualTo("MySeed");

        then(userService).should().findByName("userX");
        then(seedService).should().save(7L, "XYZ", "MySeed");
    }

    @Test
    @DisplayName("Given an authenticated user and seed ID, when delete is called, then deletes seed and returns 204")
    void givenAuthenticatedUserAndId_whenDelete_thenDeletesAndReturnsNoContent() {
        given(auth.isAuthenticated()).willReturn(true);
        given(auth.getName()).willReturn("deleter");
        User u = User.builder().id(5L).name("deleter").build();
        given(userService.findByName("deleter")).willReturn(u);

        ResponseEntity<Void> resp = controller.delete(auth, 123L);

        assertThat(resp.getStatusCodeValue()).isEqualTo(204);
        then(userService).should().findByName("deleter");
        then(seedService).should().delete(5L, 123L);
    }
}