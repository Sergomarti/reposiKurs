package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.PaymentListResponse;
import com.upgrad.FoodOrderingApp.api.model.PaymentResponse;
import com.upgrad.FoodOrderingApp.service.businness.PaymentService;
import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * This api endpoint is used retrieve all the payment methods
     *
     * @return ResponseEntity<PaymentListResponse> type object along with HttpStatus OK
     */

    @RequestMapping(method = RequestMethod.GET, path = "/payment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PaymentListResponse> getAllPaymentMethods() {

        // get all payment methods
        List<PaymentEntity> paymentEntityList = paymentService.getAllPaymentMethods();

        // create response
        PaymentListResponse paymentListResponse = new PaymentListResponse();

        for (PaymentEntity paymentEntity : paymentEntityList) {
            PaymentResponse paymentResponse = new PaymentResponse()
                    .id(UUID.fromString(paymentEntity.getUuid()))
                    .paymentName(paymentEntity.getPaymentName());
            paymentListResponse.addPaymentMethodsItem(paymentResponse);
        }

        return new ResponseEntity<PaymentListResponse>(paymentListResponse, HttpStatus.OK);
    }
}
