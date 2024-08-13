package com.example.test_join.dto.server.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DualResponseDTO {
    private String ddDate;
    private String outStanding;
    private String countLoan;
    private String maturity;
    private String settleDate;
}
