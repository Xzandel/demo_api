package com.domain.demo_api.models.dto.request;

public class RekeningRequest {
    private Integer idRekening;
    private String namaPemilik;
    private Long saldoRekening;
    private String currency;

    public Integer getIdRekening() {
        return this.idRekening;
    }

    public void setIdRekening(Integer idRekening) {
        this.idRekening = idRekening;
    }

    public String getNamaPemilik() {
        return this.namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public Long getSaldoRekening() {
        return this.saldoRekening;
    }

    public void setSaldoRekening(Long saldoRekening) {
        this.saldoRekening = saldoRekening;
    }

    public String getCurrency() {
        return this.currency.toUpperCase();
    }

    public void setCurrency(String currency) {
        this.currency = currency.toUpperCase();
    }

}
