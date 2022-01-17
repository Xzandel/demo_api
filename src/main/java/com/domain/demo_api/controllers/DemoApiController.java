package com.domain.demo_api.controllers;

import com.domain.demo_api.models.dto.request.BuktiTransferRequest;
import com.domain.demo_api.models.dto.request.RekeningRequest;
import com.domain.demo_api.models.dto.response.BuktiTransferResponse;
import com.domain.demo_api.models.dto.response.RekeningResponse;
import com.domain.demo_api.services.BuktiTransferService;
import com.domain.demo_api.services.RekeningService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankxyz")
public class DemoApiController {

    @Autowired
    private RekeningService rekService;
    @Autowired
    private BuktiTransferService btService;

    // Register rekening

    // Untuk ngetes custom exception
    // @GetMapping("/test")
    // public String welcome() {
    // String a = "a";
    // if (a == "b") {
    // return a;
    // } else {
    // throw new ApiRequestException("Oops");
    // }

    // }

    @PostMapping("/register")
    public ResponseEntity<RekeningResponse> newRekening(@RequestBody RekeningRequest rekRequest) {
        return new ResponseEntity<RekeningResponse>(rekService.newRekening(rekRequest), HttpStatus.CREATED);
    }

    @GetMapping("/saldo/={id}")
    public ResponseEntity<RekeningResponse> getSaldoRekening(@PathVariable("id") Integer id) {
        return new ResponseEntity<RekeningResponse>(rekService.getSaldoRekening(id), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<BuktiTransferResponse> newTransfer(@RequestBody BuktiTransferRequest btRequest) {
        return new ResponseEntity<BuktiTransferResponse>(btService.newTransfer(btRequest), HttpStatus.CREATED);
    }

}
