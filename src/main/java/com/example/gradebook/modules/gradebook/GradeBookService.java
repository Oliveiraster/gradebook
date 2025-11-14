package com.example.gradebook.modules.gradebook;


import com.example.gradebook.modules.gradebook.dto.BoletimResponseDTO;
import com.example.gradebook.modules.gradebook.dto.GradeRequestDTO;
import com.example.gradebook.modules.gradebook.model.LancamentoNota;
import com.example.gradebook.modules.student.StudentRepository;
import com.example.gradebook.modules.student.model.Student;
import com.example.gradebook.modules.subject.AvaliacaoRepository;
import com.example.gradebook.modules.subject.model.Avaliacao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeBookService {
    private final LancamentoNotaRepository lancamentoNotaRepository;
    private final StudentRepository studentRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    public List<BoletimResponseDTO>obterBoletim(Long turmaId, Long disciplinaId) {
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

            BigDecimal media = this.calcularMediaPonderada(notasAluno.stream()
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

    public BigDecimal calcularMediaPonderada(List<LancamentoNota> lancamentos) {
        if (lancamentos == null || lancamentos.isEmpty()) {
            return null;
        }

        BigDecimal somaNotaVezesPeso = BigDecimal.ZERO;
        int somaPesos = 0;

        for (LancamentoNota lancamento : lancamentos) {
            BigDecimal nota = lancamento.getNota();
            int peso = lancamento.getAvaliacao().getPeso();

            somaNotaVezesPeso = somaNotaVezesPeso.add(nota.multiply(BigDecimal.valueOf(peso)));

            somaPesos += peso;
        }

        if (somaPesos == 0) {
            return null;
        }

        return somaNotaVezesPeso.divide(BigDecimal.valueOf(somaPesos), 2, RoundingMode.HALF_UP);
    }

    @Transactional
    public void salvarLancamentosEmLote(List<GradeRequestDTO> lancamentosDto) {
        for (GradeRequestDTO dto : lancamentosDto) {

            Optional<Student> alunoOpt = studentRepository.findById(dto.getStudentId());
            Optional<Avaliacao> avaliacaoOpt = avaliacaoRepository.findById(dto.getAssessmentId());

            if (alunoOpt.isEmpty() || avaliacaoOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno ou Avaliação não encontrado para os IDs: " + dto.getStudentId() + "/" + dto.getAssessmentId());
            }

            Student aluno = alunoOpt.get();
            Avaliacao avaliacao = avaliacaoOpt.get();

            LancamentoNota lancamento = lancamentoNotaRepository
                    .findByAlunoIdAndAvaliacaoId(aluno.getId(), avaliacao.getId());

            if (lancamento == null) {
                lancamento = new LancamentoNota();
                lancamento.setAluno(aluno);
                lancamento.setAvaliacao(avaliacao);
            }

            lancamento.setNota(dto.getScore());
            lancamentoNotaRepository.save(lancamento);
        }
    }
}
