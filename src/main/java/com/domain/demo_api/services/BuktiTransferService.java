package com.domain.demo_api.services;

import com.domain.demo_api.models.dto.request.BuktiTransferRequest;
import com.domain.demo_api.models.dto.response.BuktiTransferResponse;

public interface BuktiTransferService {
    BuktiTransferResponse newTransfer(BuktiTransferRequest btRequest);
}
