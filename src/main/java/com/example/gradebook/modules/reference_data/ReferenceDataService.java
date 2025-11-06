package com.example.gradebook.modules.reference_data;

import com.example.gradebook.modules.class_school.TurmaRepository;
import com.example.gradebook.modules.class_school.dto.ClassResponseDTO;
import com.example.gradebook.modules.subject.DisciplinaRepository;
import com.example.gradebook.modules.subject.dto.SubjectResponseDTO;
import com.example.gradebook.modules.class_school.model.Turma;
import com.example.gradebook.modules.subject.model.Disciplina;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferenceDataService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public List<ClassResponseDTO> listarTurmas() {
        List<Turma> turmas = turmaRepository.findAll();
        return turmas.stream()
                .map(turma -> new ClassResponseDTO(turma.getId(), turma.getNome()))
                .collect(Collectors.toList());
    }

    public List<SubjectResponseDTO> listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        return disciplinas.stream()
                .map(disciplina -> new SubjectResponseDTO(disciplina.getId(), disciplina.getNome()))
                .collect(Collectors.toList());
    }
}
