package com.domain.demo_api.services.impl;

import com.domain.demo_api.models.dto.request.BuktiTransferRequest;
import com.domain.demo_api.models.dto.response.BuktiTransferResponse;
import com.domain.demo_api.models.entities.BuktiTransfer;
import com.domain.demo_api.models.entities.Rekening;
import com.domain.demo_api.models.repo.BuktiTransferRepo;
import com.domain.demo_api.models.repo.RekeningRepo;
import com.domain.demo_api.services.BuktiTransferService;
import com.domain.demo_api.validators.Validation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuktiTransferImpl implements BuktiTransferService {

    @Autowired
    BuktiTransferRepo btRepo;
    @Autowired
    RekeningRepo rekRepo;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public BuktiTransferResponse newTransfer(BuktiTransferRequest btRequest) {
        BuktiTransferResponse btResponse = new BuktiTransferResponse();

        if (Validation.checkPengirimPenerima(rekRepo, btRepo, btRequest)) {
            if (Validation.checkCurrency(rekRepo, btRepo, btRequest)) {
                if (Validation.checkJumlahTransfer(btRequest.getJumlahTransfer())) {
                    if (Validation.checkRequiredSaldo(rekRepo, btRepo, btRequest)) {

                        BuktiTransfer buktiTransfer = new BuktiTransfer();
                        Rekening rekening = new Rekening();

                        Integer idRekeningPengirim = btRequest.getIdRekeningPengirim();
                        Integer idRekeningPenerima = btRequest.getIdRekeningPenerima();
                        Long jumlahTransfer = btRequest.getJumlahTransfer();

                        rekening = rekRepo.getById(idRekeningPenerima);
                        rekening.setSaldoRekening(rekening.getSaldoRekening() + jumlahTransfer);
                        rekRepo.save(rekening);

                        rekening = rekRepo.getById(idRekeningPengirim);
                        rekening.setSaldoRekening(rekening.getSaldoRekening() - jumlahTransfer);
                        rekRepo.save(rekening);

                        modelMapper.map(btRequest, buktiTransfer);
                        btRepo.save(buktiTransfer);
                        modelMapper.map(buktiTransfer, btResponse);
                    }
                }
            }
        }

        return btResponse;
    }

}
