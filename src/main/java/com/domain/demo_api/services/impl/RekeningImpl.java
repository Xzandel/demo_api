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
    // ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ModelMapper mapper;

    @Override
    public RekeningResponse newRekening(RekeningRequest rekRequest) {
        RekeningResponse rekResponse = new RekeningResponse();
        Validation.checkRekeningNullFields(rekRequest);
        Validation.checkRekeningExist(rekRequest, rekRepo);
        Rekening rekening = new Rekening();

        mapper.map(rekRequest, rekening);

        rekRepo.save(rekening);

        mapper.map(rekening, rekResponse);

        return rekResponse;
    }

    @Override
    public RekeningResponse getSaldoRekening(Integer idRekening) {
        RekeningResponse rekResponse = new RekeningResponse();

        Validation.checkRekeningExistByID(idRekening, rekRepo);
        mapper.map(rekRepo.findById(idRekening).get(), rekResponse);

        return rekResponse;
    }

}
