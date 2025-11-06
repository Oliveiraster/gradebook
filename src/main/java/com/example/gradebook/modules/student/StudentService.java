package com.example.gradebook.modules.student;

import com.example.gradebook.modules.student.dto.StudentRequestDTO;
import com.example.gradebook.modules.student.model.Student;
import com.example.gradebook.modules.class_school.TurmaRepository;
import com.example.gradebook.modules.class_school.model.Turma;
import com.example.gradebook.modules.gradebook.LancamentoNotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TurmaRepository turmaRepository;
    private final LancamentoNotaRepository lancamentoNotaRepository;

    @Transactional
    public Student createStudent(Long turmaId, StudentRequestDTO dto) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));

        Student aluno = new Student();
        aluno.setNome(dto.getName());
        aluno.setTurma(turma);

        return studentRepository.save(aluno);
    }

    @Transactional
    public Optional<Student> updateStudent(Long id, StudentRequestDTO dto) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setNome(dto.getName());
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
        // Deleção de notas
        lancamentoNotaRepository.deleteByAlunoId(aluno.getId());
        studentRepository.delete(aluno);
        return true;
    }
}
