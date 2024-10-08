package com.example.test_join.dto.server.request;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAccountRequest {
    @JsonProperty("client_no")
    private String clientNo;
    @JsonProperty("ctrl_branch")
    private String ctrlBranch;

}
