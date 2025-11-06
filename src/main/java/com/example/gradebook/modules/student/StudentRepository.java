package com.example.gradebook.modules.student;

import com.example.gradebook.modules.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByTurmaId(Long turmaId);
}
