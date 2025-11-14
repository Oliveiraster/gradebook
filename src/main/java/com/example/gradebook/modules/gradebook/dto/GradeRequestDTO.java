package com.example.gradebook.modules.gradebook.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeRequestDTO {
    @NotNull(message = "O ID do aluno é obrigatório.")
    private Long studentId;

    @NotNull(message = "O ID da avaliação é obrigatório.")
    private Long assessmentId;

    @NotNull(message = "A nota é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota mínima deve ser 0.0")
    @DecimalMax(value = "10.0", message = "A nota máxima deve ser 10.0")
    private BigDecimal score;
}
