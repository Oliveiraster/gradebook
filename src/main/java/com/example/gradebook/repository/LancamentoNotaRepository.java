package com.example.gradebook.repository;

import com.example.gradebook.model.LancamentoNota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoNotaRepository extends JpaRepository<LancamentoNota, Long> {

    List<LancamentoNota> findByAlunoId(Long alunoId);

    LancamentoNota findByAlunoIdAndAvaliacaoId(Long alunoId, Long avaliacaoId);
}

