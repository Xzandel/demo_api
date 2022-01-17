package com.domain.demo_api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_bukti_transfer")
public class BuktiTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransfer;
    @Column(name = "idRekeningPengirim")
    private Integer idRekeningPengirim;
    @Column(name = "idRekeningPenerima")
    private Integer idRekeningPenerima;
    @Column(name = "jumlahTransfer")
    private Long jumlahTransfer;
    @Column(name = "currency")
    // @NotNull
    private String currency;
}
