package com.example.test_join.dto.server.response;

import com.example.test_join.dto.client.response.CustomerClientResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private String clientName;
    private String clientShort;
    private String clientType;
    @JsonProperty("country")
    private String countryDesc;
    @JsonProperty("state")
    private String stateLoc;
    private String clientNo;
    private String globalId;
    private String taxFileNo;
    @JsonProperty("branch")
    private String ctrlBranch;
    private String branchName;
    @JsonProperty("date_of_issue")
    private String dtOfIssuance; 
    @JsonProperty("place_of_issue")
    private String placeOfIssuance;
    private String registeredCapital;
    private String acctExec; 
    private String addressPo1;
    private String addressPo2;
    private String addressPo3;
    private String generalDirectorName;
    private String presidentName;
    private String chiefAccountantName;
    
    public static CustomerResponseDTO fromCustomerClientResponse(CustomerClientResponse customerClientResponse) {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setClientName(customerClientResponse.getClientName());
        customerResponseDTO.setClientShort(customerClientResponse.getClientShort());
        customerResponseDTO.setClientType(customerClientResponse.getClientType());
        customerResponseDTO.setStateLoc(customerClientResponse.getStateLoc());
        customerResponseDTO.setClientNo(customerClientResponse.getClientNo());
        customerResponseDTO.setGlobalId(customerClientResponse.getGlobalId());
        customerResponseDTO.setTaxFileNo(customerClientResponse.getTaxFileNo());
        customerResponseDTO.setCtrlBranch(customerClientResponse.getCtrlBranch());
        customerResponseDTO.setDtOfIssuance(customerClientResponse.getDtOfIssuance());
        customerResponseDTO.setPlaceOfIssuance(customerClientResponse.getPlaceOfIssuance());
        customerResponseDTO.setRegisteredCapital(customerClientResponse.getRegisteredCapital());
        customerResponseDTO.setAcctExec(customerClientResponse.getAcctExec());
        customerResponseDTO.setAddressPo1(customerClientResponse.getAddressPo1());
        customerResponseDTO.setAddressPo2(customerClientResponse.getAddressPo2());
        customerResponseDTO.setAddressPo3(customerClientResponse.getAddressPo3());
        customerResponseDTO.setGeneralDirectorName(customerClientResponse.getGeneralDirectorName());
        customerResponseDTO.setPresidentName(customerClientResponse.getPresidentName());
        customerResponseDTO.setChiefAccountantName(customerClientResponse.getChiefAccountantName());
        return customerResponseDTO;
    }
}
