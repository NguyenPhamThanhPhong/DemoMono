package com.example.test_join.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test_join.dto.client.response.ExchangeRateClientResponse;
import com.example.test_join.dto.client.response.LoanDetailClientResponse;
import com.example.test_join.dto.server.response.ExchangeRateResponseDTO;
import com.example.test_join.dto.server.response.LoanDetailResponseDTO;

@Mapper(componentModel = "spring")
public interface IFinanceMapper {
    IFinanceMapper INSTANCE = Mappers.getMapper(IFinanceMapper.class);

    public ExchangeRateResponseDTO fromExchangeRateResponseClientToDTO(ExchangeRateClientResponse source);
    public LoanDetailResponseDTO fromLoanDetailResponseClientToDTO(LoanDetailClientResponse source);
}
