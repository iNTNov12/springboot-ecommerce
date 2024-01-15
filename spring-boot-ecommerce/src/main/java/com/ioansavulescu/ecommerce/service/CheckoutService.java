package com.ioansavulescu.ecommerce.service;

import com.ioansavulescu.ecommerce.dto.Cumparare;
import com.ioansavulescu.ecommerce.dto.CumparareResponse;
import com.ioansavulescu.ecommerce.dto.PaymentInfo;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    CumparareResponse puneComanda(Cumparare cumparare);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
