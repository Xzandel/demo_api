package com.domain.demo_api.models.dto.response;

import lombok.Data;

@Data
public class BuktiTransferResponse {
    private Integer idTransfer;
    private Integer idRekeningPengirim;
    private Integer idRekeningPenerima;
    private Long jumlahTransfer;
    private String currency;

}
