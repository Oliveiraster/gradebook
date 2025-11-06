package com.example.gradebook.modules.student;

import com.example.gradebook.modules.class_school.TurmaRepository;
import com.example.gradebook.modules.gradebook.LancamentoNotaRepository;
import com.example.gradebook.modules.student.model.Student;
import com.example.gradebook.modules.class_school.model.Turma;
import com.example.gradebook.modules.gradebook.model.LancamentoNota;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TurmaRepository turmaRepository;
    private final LancamentoNotaRepository lancamentoNotaRepository;

    @Transactional
    public Student createStudent(Long turmaId, Student aluno) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        aluno.setTurma(turma);
        return studentRepository.save(aluno);
    }

    @Transactional
    public Optional<Student> updateStudent(Long id, Student updatedAluno) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setNome(updatedAluno.getNome());
                    return studentRepository.save(student);
                });
    }

    @Transactional
    public boolean deleteStudent(Long id) {
        Optional<Student> alunoOpt = studentRepository.findById(id);
        if (alunoOpt.isEmpty()) {
            return false;
        }

        Student aluno = alunoOpt.get();
        List<LancamentoNota> notas = lancamentoNotaRepository.findByAlunoId(aluno.getId());
        lancamentoNotaRepository.deleteAll(notas);
        studentRepository.delete(aluno);
        return true;
    }
}
