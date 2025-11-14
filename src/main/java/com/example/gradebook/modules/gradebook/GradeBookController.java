package com.example.gradebook.modules.gradebook;

import com.example.gradebook.modules.gradebook.dto.BoletimResponseDTO;
import com.example.gradebook.modules.gradebook.dto.GradeRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "Boletim", description = "Operações relacionadas ao boletim dos alunos")
@RestController
@RequestMapping("grades")
@RequiredArgsConstructor
public class GradeBookController {

    private final GradeBookService gradebookService;

    @Operation(summary = "Lista boletim por turma e disciplina",
            description = "Retorna os alunos da turma com suas notas e médias ponderadas para uma disciplina específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boletim retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Turma ou disciplina não encontrada")
    })
    @GetMapping("/turma/{turmaId}/disciplina/{disciplinaId}")
    public List<BoletimResponseDTO> listarBoletim(
            @PathVariable Long turmaId,
            @PathVariable Long disciplinaId) {

    List<BoletimResponseDTO> boletim = gradebookService.obterBoletim(turmaId, disciplinaId);
    return boletim;


    }

    @Operation(summary = "Salva notas lançadas",
            description = "Permite o lançamento em lote das notas dos alunos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notas salvas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping()
    public ResponseEntity<GradeRequestDTO> salvarLancamentos(
            @Valid @RequestBody List<GradeRequestDTO> lancamentos) {

        if (lancamentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        gradebookService.salvarLancamentosEmLote(lancamentos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
