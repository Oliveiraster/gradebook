package com.example.gradebook.modules.reference_data;


import com.example.gradebook.modules.subject.dto.SubjectResponseDTO;
import com.example.gradebook.modules.class_school.dto.ClassResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Catálogos", description = "Listagem de turmas e disciplinas disponíveis")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReferenceDataController {

    private final ReferenceDataService referenceDataService;

    @Operation(summary = "Lista todas as turmas", description = "Retorna todas as turmas cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de turmas retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma turma encontrada")
    })
    @GetMapping("/turmas")
    public ResponseEntity<List<ClassResponseDTO>> listarTurmas() {
        var turmas = referenceDataService.listarTurmas();
        if (turmas.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(turmas);
    }

    @Operation(summary = "Lista todas as disciplinas", description = "Retorna todas as disciplinas cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de disciplinas retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma disciplina encontrada")
    })
    @GetMapping("/disciplinas")
    public ResponseEntity<List<SubjectResponseDTO>> listarDisciplinas() {
        var disciplinas = referenceDataService.listarDisciplinas();
        if (disciplinas.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(disciplinas);
    }
}
