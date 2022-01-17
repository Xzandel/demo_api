package com.domain.demo_api.services.impl;

import com.domain.demo_api.models.dto.request.RekeningRequest;
import com.domain.demo_api.models.dto.response.RekeningResponse;
import com.domain.demo_api.models.entities.Rekening;
import com.domain.demo_api.models.repo.RekeningRepo;
import com.domain.demo_api.services.RekeningService;

import com.domain.demo_api.validators.Validation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RekeningImpl implements RekeningService {

    @Autowired
    private RekeningRepo rekRepo;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RekeningResponse newRekening(RekeningRequest rekRequest) {
        RekeningResponse rekResponse = new RekeningResponse();
        if (Validation.checkRekeningNullFields(rekRequest)) {

            if (Validation.checkRekeningExist(rekRequest, rekRepo)) {
                Rekening rekening = new Rekening();

                modelMapper.map(rekRequest, rekening);

                rekRepo.save(rekening);

                modelMapper.map(rekening, rekResponse);
            }
        }
        return rekResponse;
    }

    @Override
    public RekeningResponse getSaldoRekening(Integer idRekening) {
        RekeningResponse rekResponse = new RekeningResponse();

        if (Validation.checkRekeningExistByID(idRekening, rekRepo)) {
            modelMapper.map(rekRepo.findById(idRekening).get(), rekResponse);
        }

        return rekResponse;
    }

}
