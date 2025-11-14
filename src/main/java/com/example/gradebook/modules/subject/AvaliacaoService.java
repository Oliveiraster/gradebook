package com.example.gradebook.modules.subject;

import com.example.gradebook.modules.subject.dto.AvaliacaoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;

    @Transactional
    public List<AvaliacaoResponseDTO> findByDisciplinaId(Long disciplinaId) {
        return avaliacaoRepository.findByDisciplinaId(disciplinaId)
                .stream()
                .map(a -> AvaliacaoResponseDTO.builder()
                        .id(a.getId())
                        .title(a.getTitulo())
                        .weight(a.getPeso())
                        .build())
                .toList();
    }
}