package com.domain.demo_api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNullFields;

// import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

/**
 * Rekening
 */
@Data
@Entity
@Table(name = "tbl_rekening")
public class Rekening {
    @Id
    private Integer idRekening;
    @Column(name = "namaPemilik")
    // @NotNull
    private String namaPemilik;
    @Column(name = "saldoRekening")
    Long saldoRekening;
    @Column(name = "currency")
    // @NotNull
    private String currency;
}