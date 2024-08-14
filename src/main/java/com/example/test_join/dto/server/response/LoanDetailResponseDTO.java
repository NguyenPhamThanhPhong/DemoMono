package com.example.test_join.dto.server.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoanDetailResponseDTO {
    private String creditNo;
    private String loanNo;
    private String loanType;
    private String loanSubType;
    private String borrower;
    private String bookBranch;
    private String ccy;
    private String startDate;
    private String matureDate;
    private String originalLoanAMT;
    private String outStanding;
}
