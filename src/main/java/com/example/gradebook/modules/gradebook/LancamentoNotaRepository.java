package com.example.gradebook.modules.gradebook;

import com.example.gradebook.modules.gradebook.model.LancamentoNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LancamentoNotaRepository extends JpaRepository<LancamentoNota, Long> {

    List<LancamentoNota> findByAlunoId(Long alunoId);

    @Query("SELECT l FROM LancamentoNota l WHERE l.aluno.id = :alunoId AND l.avaliacao.id = :avaliacaoId")
    LancamentoNota findByAlunoIdAndAvaliacaoId(@Param("alunoId") Long alunoId,
                                               @Param("avaliacaoId") Long avaliacaoId);

}

