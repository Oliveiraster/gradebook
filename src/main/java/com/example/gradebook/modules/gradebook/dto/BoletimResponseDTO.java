package com.example.gradebook.modules.gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoletimResponseDTO {
    private Long studentId;
    private String studentName;
    private Map<Long, BigDecimal> grades;
    private BigDecimal weightedAverage;
}
