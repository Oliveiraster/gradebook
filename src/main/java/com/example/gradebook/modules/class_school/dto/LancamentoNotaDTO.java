package com.example.gradebook.modules.class_school.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LancamentoNotaDTO {
    @NotNull(message = "O ID do aluno é obrigatório.")
    private Long alunoId;

    @NotNull(message = "O ID da avaliação é obrigatório.")
    private Long avaliacaoId;

    @NotNull(message = "A nota é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota mínima deve ser 0.0")
    @DecimalMax(value = "10.0", message = "A nota máxima deve ser 10.0")
    private BigDecimal nota;
}
