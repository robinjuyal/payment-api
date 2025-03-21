package com.paymentApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymentApi.request.PaymentServiceRequest;
import com.paymentApi.response.PaymentServiceResponse;
import com.paymentApi.service.PaymentService;


@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/get_service")
    public ResponseEntity<PaymentServiceResponse> getService(@RequestBody PaymentServiceRequest request) {
        PaymentServiceResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }
}
