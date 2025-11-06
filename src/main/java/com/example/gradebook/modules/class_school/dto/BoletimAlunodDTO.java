package com.example.gradebook.modules.class_school.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class BoletimAlunodDTO {
    private Long alunoId;
    private String nomeAluno;

    private Map<Long, BigDecimal> notasPorAvaliacao;

    private BigDecimal mediaPonderada;
}
