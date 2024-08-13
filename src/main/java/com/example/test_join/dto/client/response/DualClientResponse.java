package com.example.test_join.dto.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DualClientResponse {
    private String ddDate;
    private String outStanding;
    private String countLoan;
    private String maturity;
    private String settleDate;

    public static DualClientResponse template(){
        return DualClientResponse.builder()
                .ddDate("ddDate")
                .outStanding("outStanding")
                .countLoan("countLoan")
                .maturity("maturity")
                .settleDate("settleDate")
                .build();
    }
}
