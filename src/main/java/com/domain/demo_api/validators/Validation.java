package com.domain.demo_api.validators;

import java.lang.reflect.Field;

import com.domain.demo_api.models.dto.request.BuktiTransferRequest;
import com.domain.demo_api.models.dto.request.RekeningRequest;
import com.domain.demo_api.models.entities.Rekening;
import com.domain.demo_api.models.repo.BuktiTransferRepo;
import com.domain.demo_api.models.repo.RekeningRepo;
import com.domain.demo_api.services.exception.ApiRequestException;

public class Validation {

    // REKENING IMPLEMENTATION
    public static boolean checkRekeningExist(RekeningRequest rekRequest, RekeningRepo rekRepo) {

        if (!rekRepo.existsById(rekRequest.getIdRekening())) {
            return true;
        } else {
            throw new ApiRequestException("Rekening sudah terdaftar!!");
        }
    }

    public static boolean checkRekeningExistByID(Integer id, RekeningRepo rekRepo) {
        if (rekRepo.existsById(id)) {
            return true;
        } else {
            throw new ApiRequestException("Rekening tidak ditemukan");
        }
    }

    public static boolean checkRekeningNullFields(RekeningRequest rekRequest) {
        if (rekRequest.getCurrency() == null) {
            throw new ApiRequestException("Bad Request : Null Currency");
        }

        if (rekRequest.getIdRekening() == null) {
            throw new ApiRequestException("Bad Request : Null Id Rekening");
        }
        if (rekRequest.getNamaPemilik() == null) {
            throw new ApiRequestException("Bad Request : Null Nama Pemilik");
        }
        if (rekRequest.getSaldoRekening() == null) {
            throw new ApiRequestException("Bad Request : Null Saldo");
        }

        return true;

    }

    // BUKTI TRANSFER IMPLEMENTATION

    public static boolean checkPengirimPenerima(RekeningRepo rekRepo, BuktiTransferRepo btRepo,
            BuktiTransferRequest btRequest) {

        Integer idRekeningPengirim = btRequest.getIdRekeningPengirim();
        Integer idRekeningPenerima = btRequest.getIdRekeningPenerima();

        if (idRekeningPenerima.equals(idRekeningPengirim)) {
            throw new ApiRequestException("Pengirim dan tujuan harus berbeda!");
        }

        if (!rekRepo.existsById(idRekeningPenerima) && !rekRepo.existsById(idRekeningPengirim)) {
            throw new ApiRequestException("Kedua rekening tidak ditemukan");
        }
        if ((!rekRepo.existsById(idRekeningPengirim))) {
            throw new ApiRequestException("ID Rekening pengirim tidak ditemukan");
        }
        if ((!rekRepo.existsById(idRekeningPenerima))) {
            throw new ApiRequestException("ID Rekening penerima tidak ditemukan");
        }

        return true;

    }

    public static boolean checkCurrency(RekeningRepo rekRepo, BuktiTransferRepo btRepo,
            BuktiTransferRequest btRequest) {
        Rekening rekPengirim = new Rekening();

        Rekening rekPenerima = new Rekening();

        Integer idPengirim = btRequest.getIdRekeningPengirim();
        Integer idPenerima = btRequest.getIdRekeningPenerima();

        rekPengirim = rekRepo.getById(idPengirim);
        rekPenerima = rekRepo.getById(idPenerima);

        String pengirimCurrency = rekPengirim.getCurrency();
        String penerimaCurrency = rekPenerima.getCurrency();

        if (!pengirimCurrency.equals(penerimaCurrency)) {
            throw new ApiRequestException("Currency pengirim dan penerima tidak sama");
        }

        String requestedCurrency = btRequest.getCurrency();

        if (requestedCurrency.equals(pengirimCurrency) && requestedCurrency.equals(penerimaCurrency)) {
            return true;
        } else {
            throw new ApiRequestException("Currency yang diminta tidak sama");
        }

    }

    public static boolean checkJumlahTransfer(Long jumlahTransfer) {

        if (jumlahTransfer > 0) {
            return true;
        } else {
            throw new ApiRequestException("Jumlah transfer tidak boleh lebih kecil dari 0");
        }
    }

    public static boolean checkRequiredSaldo(RekeningRepo rekRepo, BuktiTransferRepo btRepo,
            BuktiTransferRequest btRequest) {
        Rekening rekeningPengirim = new Rekening();

        Long jumlahTransfer = btRequest.getJumlahTransfer();

        Integer idRekeningPengirim = btRequest.getIdRekeningPengirim();

        rekeningPengirim = rekRepo.getById(idRekeningPengirim);

        if (jumlahTransfer > rekeningPengirim.getSaldoRekening()) {
            throw new ApiRequestException("Jumlah Transfer tidak boleh melebihi saldo rekening");
        } else {
            return true;

        }

    }

}
