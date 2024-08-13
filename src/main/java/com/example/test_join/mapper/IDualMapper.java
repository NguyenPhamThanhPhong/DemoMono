package com.example.test_join.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test_join.dto.client.response.DualClientResponse;
import com.example.test_join.dto.server.response.DualResponseDTO;

@Mapper(componentModel = "spring")
public interface IDualMapper {
    IDualMapper INSTANCE = Mappers.getMapper(IDualMapper.class);
    
    public DualResponseDTO fromDualClientResponseToDTO(DualClientResponse dualClientResponse);
}
