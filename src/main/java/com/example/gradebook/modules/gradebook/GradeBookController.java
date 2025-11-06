package com.example.gradebook.modules.gradebook;

import com.example.gradebook.modules.gradebook.model.LancamentoNota;
import com.example.gradebook.modules.student.model.Student;
import com.example.gradebook.modules.student.StudentRepository;
import com.example.gradebook.modules.subject.AvaliacaoRepository;
import com.example.gradebook.modules.subject.dto.AvaliacaoResponseDTO;
import com.example.gradebook.modules.subject.model.Avaliacao;
import com.example.gradebook.modules.gradebook.dto.BoletimResponseDTO;
import com.example.gradebook.modules.gradebook.dto.GradeRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("grades")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class GradeBookController {

    private final StudentRepository studentRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final LancamentoNotaRepository lancamentoNotaRepository;
    private final GradebookService gradebookService;

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

        List<Avaliacao> avaliacoes = avaliacaoRepository.findByDisciplinaId(disciplinaId);
        Map<Long, Avaliacao> avaliacaoMap = avaliacoes.stream()
                .collect(Collectors.toMap(Avaliacao::getId, a -> a));

        List<Student> alunos = studentRepository.findByTurmaId(turmaId);

        return alunos.stream().map(aluno -> {
            List<LancamentoNota> notasAluno = lancamentoNotaRepository.findByAlunoId(aluno.getId());

            Map<Long, BigDecimal> notasPorAvaliacao = notasAluno.stream()
                    .filter(nota -> avaliacaoMap.containsKey(nota.getAvaliacao().getId()))
                    .collect(Collectors.toMap(
                            n -> n.getAvaliacao().getId(),
                            LancamentoNota::getNota
                    ));

            BigDecimal media = gradebookService.calcularMediaPonderada(notasAluno.stream()
                    .filter(n -> avaliacaoMap.containsKey(n.getAvaliacao().getId()))
                    .collect(Collectors.toList()));

            return BoletimResponseDTO.builder()
                    .studentId(aluno.getId())
                    .studentName(aluno.getNome())
                    .grades(notasPorAvaliacao)
                    .weightedAverage(media)
                    .build();
        }).collect(Collectors.toList());
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
                            .title(a.getTitulo())    // ou getNome() dependendo do seu atributo
                            .weight(a.getPeso())
                            .build())
                    .toList();
        }

    }
}
