package com.example.gradebook.modules.subject;

import com.example.gradebook.modules.subject.dto.AvaliacaoResponseDTO;
import com.example.gradebook.modules.subject.model.Avaliacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Avaliações", description = "Operações relacionadas às avaliações")
@RestController
@RequestMapping("avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;

    @Operation(summary = "Lista avaliações por disciplina",
            description = "Retorna todas as avaliações cadastradas de uma disciplina, com nome e peso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliações retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @GetMapping("/disciplina/{disciplinaId}")
    public List<AvaliacaoResponseDTO> listarPorDisciplina(@PathVariable Long disciplinaId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByDisciplinaId(disciplinaId);

        return avaliacoes.stream()
                .map(a -> AvaliacaoResponseDTO.builder()
                        .id(a.getId())
                        .title(a.getTitulo())
                        .weight(a.getPeso())
                        .build())
                .toList();
    }

}