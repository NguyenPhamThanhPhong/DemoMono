package com.example.test_join.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerClientResponse {
    @JsonProperty("client_name")
    private String clientName;
    @JsonProperty("client_short")
    private String clientShort;
    @JsonProperty("client_type")
    private String clientType;
    @JsonProperty("country")
    private String stateLoc;
    @JsonProperty("client_no")
    private String clientNo;
    @JsonProperty("global_id")
    private String globalId;
    @JsonProperty("tax_file_no")
    private String taxFileNo;
    @JsonProperty("ctrl_branch")
    private String ctrlBranch;
    @JsonProperty("country_citizen")
    private String countryCitizen;
    @JsonProperty("dt_of_issuance")
    private String dtOfIssuance; 
    @JsonProperty("place_of_issuance")
    private String placeOfIssuance;
    @JsonProperty("registered_capital")
    private String registeredCapital;
    @JsonProperty("acct_exec")
    private String acctExec; 
    @JsonProperty("address_po1")
    private String addressPo1;
    @JsonProperty("address_po2")
    private String addressPo2;
    @JsonProperty("address_po3")
    private String addressPo3;
    @JsonProperty("general_director_name")
    private String generalDirectorName;
    @JsonProperty("president_name")
    private String presidentName;
    @JsonProperty("chief_accountant_name")
    private String chiefAccountantName;
    public static CustomerClientResponse Default(){
        CustomerClientResponse customerInfoResponse = new CustomerClientResponse();
        customerInfoResponse.setClientName("Default Client");
        customerInfoResponse.setClientShort("Default Short");
        customerInfoResponse.setClientType("Default Type");
        customerInfoResponse.setStateLoc("Default Country");
        customerInfoResponse.setCountryCitizen("Default Country");
        customerInfoResponse.setClientNo("Default No");
        customerInfoResponse.setGlobalId("Default Global Id");
        customerInfoResponse.setTaxFileNo("Default Tax File No");
        customerInfoResponse.setCtrlBranch("Default Ctrl Branch");
        customerInfoResponse.setDtOfIssuance("Default Date of Issuance");
        customerInfoResponse.setPlaceOfIssuance("Default Place of Issuance");
        customerInfoResponse.setRegisteredCapital("Default Registered Capital");
        customerInfoResponse.setAcctExec("Default Acct Exec");
        customerInfoResponse.setAddressPo1("Default Address Po1");
        customerInfoResponse.setAddressPo2("Default Address Po2");
        customerInfoResponse.setAddressPo3("Default Address Po3");
        customerInfoResponse.setGeneralDirectorName("Default General Director Name");
        customerInfoResponse.setPresidentName("Default President Name");
        customerInfoResponse.setChiefAccountantName("Default Chief Accountant Name");
        return customerInfoResponse;
    }
}
