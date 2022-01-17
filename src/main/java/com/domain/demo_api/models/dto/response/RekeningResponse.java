package com.domain.demo_api.models.dto.response;

import lombok.Data;

@Data
public class RekeningResponse {
    private Integer idRekening;
    private String namaPemilik;
    private Long saldoRekening;
    private String currency;
}
