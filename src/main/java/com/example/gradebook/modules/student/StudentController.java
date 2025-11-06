package com.example.gradebook.modules.student;

import com.example.gradebook.modules.student.dto.StudentRequestDTO;
import com.example.gradebook.modules.student.dto.StudentResponseDTO;
import com.example.gradebook.modules.student.model.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name= "Estudantes", description = "Operações relacionadas aos alunos")
@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Cria um novo aluno vinculado a uma turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    @PostMapping("/turma/{turmaId}")
    public ResponseEntity<StudentResponseDTO> createStudent(
            @PathVariable Long turmaId,
            @Valid @RequestBody StudentRequestDTO dto) {

        Student created = studentService.createStudent(turmaId, dto);
        StudentResponseDTO response = new StudentResponseDTO(
                created.getId(), created.getNome(), created.getTurma().getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualiza um aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dto) {

        Optional<Student> updated = studentService.updateStudent(id, dto);
        return updated.map(student -> {
            StudentResponseDTO response = new StudentResponseDTO(
                    student.getId(), student.getNome(), student.getTurma().getId()
            );
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
