package com.domain.demo_api.services;

import com.domain.demo_api.models.dto.request.RekeningRequest;
import com.domain.demo_api.models.dto.response.RekeningResponse;

/**
 * RekeningService
 */
public interface RekeningService {
    RekeningResponse newRekening(RekeningRequest rekRequest);

    RekeningResponse getSaldoRekening(Integer idRekening);
}