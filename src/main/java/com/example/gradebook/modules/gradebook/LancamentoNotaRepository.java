package com.example.gradebook.modules.gradebook;

import com.example.gradebook.modules.gradebook.model.LancamentoNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LancamentoNotaRepository extends JpaRepository<LancamentoNota, Long> {

    List<LancamentoNota> findByAlunoId(Long alunoId);

    @Modifying
    @Query("DELETE FROM LancamentoNota l WHERE l.aluno.id = :alunoId")
    void deleteByAlunoId(Long alunoId);

    @Query("SELECT l FROM LancamentoNota l WHERE l.aluno.id = :alunoId AND l.avaliacao.id = :avaliacaoId")
    LancamentoNota findByAlunoIdAndAvaliacaoId(Long alunoId, Long avaliacaoId);
}
