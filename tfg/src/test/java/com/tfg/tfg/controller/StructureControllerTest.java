package com.tfg.tfg.controller;

import com.tfg.tfg.dto.StructureSearchInputDTO;
import com.tfg.tfg.dto.StructureListOutputDTO;
import com.tfg.tfg.services.StructureFinderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class StructureControllerTest {

    @Mock
    private StructureFinderService finderService;

    @InjectMocks
    private StructureController controller;

    @Mock
    private StructureSearchInputDTO searchInput;

    @Mock
    private StructureListOutputDTO listOutput;

    @Test
    @DisplayName("Given a valid search input, when searchStructures is called, then returns the service result")
    void givenValidSearchInput_whenSearchStructures_thenReturnsDto() {
        // Given
        given(finderService.findStructures(searchInput)).willReturn(listOutput);

        // When
        ResponseEntity<StructureListOutputDTO> response = controller.searchStructures(searchInput);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isSameAs(listOutput);
        then(finderService).should(times(1)).findStructures(searchInput);
    }

    @Test
    @DisplayName("When getStructureTypes is called, then returns the full list of types")
    void whenGetStructureTypes_thenReturnsAllTypes() {
        // When
        ResponseEntity<List<String>> response = controller.getStructureTypes();

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        List<String> types = response.getBody();
        assertThat(types).hasSize(13);
        assertThat(types).containsExactly(
                "Village",
                "Desert_Pyramid",
                "Jungle_Pyramid",
                "Jungle_Temple",
                "Swamp_Hut",
                "Igloo",
                "Ocean_Ruin",
                "Shipwreck",
                "Monument",
                "Mansion",
                "Outpost",
                "Ruined_Portal",
                "Desert_Well"
        );
    }
}
