package com.example.test_join.service.restserver.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.DualResponseDTO;
import com.example.test_join.mapper.IDualMapper;
import com.example.test_join.service.restclient.DualClient;
import com.example.test_join.service.restserver.IDualService;

import reactor.core.publisher.Mono;

@Service
public class DualServiceImpl implements IDualService {
    @Autowired
    private IDualMapper dualMapper;
    @Autowired
    private DualClient dualClient;

    @Override
    public Mono<BaseResponse<DualResponseDTO>> getDualInfo(BaseRequest<?> baseRequest) {
        return dualClient.getDualInfo(baseRequest).flatMap(
            baseClientRes ->{
                DualResponseDTO dualResponseDTO = dualMapper.fromDualClientResponseToDTO(baseClientRes.getData());
                BaseResponse<DualResponseDTO> baseResponse = BaseResponse.fromBaseRequest(baseRequest);
                baseResponse.setData(dualResponseDTO);
                return Mono.just(baseResponse);
            }
        );
    }

}
