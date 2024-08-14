package com.example.test_join.dto.client.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoanDetailClientResponse {
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
    public static LoanDetailClientResponse template() {
        return LoanDetailClientResponse.builder()
                .creditNo("creditNo")
                .loanNo("loanNo")
                .loanType("loanType")
                .loanSubType("loanSubType")
                .borrower("borrower")
                .bookBranch("bookBranch")
                .ccy("ccy")
                .startDate("startDate")
                .matureDate("matureDate")
                .originalLoanAMT("originalLoanAMT")
                .outStanding("outStanding")
                .build();
    }
}
