package com.landgraf.cepProject.services;

import com.landgraf.cepProject.dto.AddressDTO;
import com.landgraf.cepProject.entities.enums.IntegrationType;
import com.landgraf.cepProject.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViaCepService {

    private final RestClient restClient;
    private final ViaCepFeignClient feignClient;

    public AddressDTO findCep(String cep, IntegrationType integrationType){

        IntegrationType strategy = (integrationType == null) ? IntegrationType.FEIGN : integrationType;

        log.info(">>>> [INTEGRAÇÃO] Buscando o CEP {} via estratégia: {} <<<<", cep, strategy);

        if (strategy == IntegrationType.REST) {
            return restClient.get()
                    .uri("/{cep}/json/", cep)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        throw new ResourceNotFoundException("CEP not found or invalid.");
                    })
                    .body(AddressDTO.class);
        } else {
            try {
                return feignClient.findAddressByCep(cep);
            } catch (Exception e) {
                throw new ResourceNotFoundException("CEP not found or invalid via Feign.");
            }
        }
    }
}

