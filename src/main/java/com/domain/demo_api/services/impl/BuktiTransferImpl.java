package com.domain.demo_api.services.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.domain.demo_api.models.dto.request.BuktiTransferRequest;
import com.domain.demo_api.models.dto.response.BuktiTransferResponse;
import com.domain.demo_api.models.entities.BuktiTransfer;
import com.domain.demo_api.models.entities.Rekening;
import com.domain.demo_api.models.repo.BuktiTransferRepo;
import com.domain.demo_api.models.repo.RekeningRepo;
import com.domain.demo_api.services.BuktiTransferService;
import com.domain.demo_api.validators.Validation;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuktiTransferImpl implements BuktiTransferService {

    @Autowired
    BuktiTransferRepo btRepo;
    @Autowired
    RekeningRepo rekRepo;

    ModelMapper mapper = new ModelMapper();

    @Override
    public BuktiTransferResponse newTransfer(BuktiTransferRequest btRequest) {
        BuktiTransferResponse btResponse = new BuktiTransferResponse();

        Validation.checkBuktiTransferNullFields(btRequest);
        Validation.checkPengirimPenerima(rekRepo, btRepo, btRequest);
        Validation.checkCurrency(rekRepo, btRepo, btRequest);
        Validation.checkJumlahTransfer(btRequest.getJumlahTransfer());
        Validation.checkRequiredSaldo(rekRepo, btRepo, btRequest);

        // BuktiTransfer buktiTransfer = new BuktiTransfer();
        Rekening rekeningPengirim = new Rekening();
        Rekening rekeningPenerima = new Rekening();

        Integer idRekeningPengirim = btRequest.getIdRekeningPengirim();
        Integer idRekeningPenerima = btRequest.getIdRekeningPenerima();
        Long jumlahTransfer = btRequest.getJumlahTransfer();

        rekeningPenerima = rekRepo.getById(idRekeningPenerima);
        rekeningPenerima.setSaldoRekening(rekeningPenerima.getSaldoRekening() + jumlahTransfer);

        rekeningPengirim = rekRepo.getById(idRekeningPengirim);
        rekeningPengirim.setSaldoRekening(rekeningPengirim.getSaldoRekening() - jumlahTransfer);
        List<Rekening> rekenings = new ArrayList<Rekening>();
        rekRepo.saveAll(rekenings);

        BuktiTransfer buktiTransfer = new BuktiTransfer();
        mapper.map(btRequest, buktiTransfer);
        btRepo.save(buktiTransfer);
        mapper.map(buktiTransfer, btResponse);

        return btResponse;
    }

}
