package com.domain.demo_api.models.dto.request;

public class BuktiTransferRequest {
    private Integer idTransfer;
    private Integer idRekeningPengirim;
    private Integer idRekeningPenerima;
    private Long jumlahTransfer;
    private String currency;

    public Integer getIdTransfer() {
        return this.idTransfer;
    }

    public void setIdTransfer(Integer idTransfer) {
        this.idTransfer = idTransfer;
    }

    public Integer getIdRekeningPengirim() {
        return this.idRekeningPengirim;
    }

    public void setIdRekeningPengirim(Integer idRekeningPengirim) {
        this.idRekeningPengirim = idRekeningPengirim;
    }

    public Integer getIdRekeningPenerima() {
        return this.idRekeningPenerima;
    }

    public void setIdRekeningPenerima(Integer idRekeningPenerima) {
        this.idRekeningPenerima = idRekeningPenerima;
    }

    public Long getJumlahTransfer() {
        return this.jumlahTransfer;
    }

    public void setJumlahTransfer(Long jumlahTransfer) {
        this.jumlahTransfer = jumlahTransfer;
    }

    public String getCurrency() {
        return this.currency.toUpperCase();
    }

    public void setCurrency(String currency) {
        this.currency = currency.toUpperCase();
    }

}
