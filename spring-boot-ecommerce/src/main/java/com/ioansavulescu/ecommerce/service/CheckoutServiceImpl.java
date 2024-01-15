package com.ioansavulescu.ecommerce.service;

import com.ioansavulescu.ecommerce.dao.ClientRepository;
import com.ioansavulescu.ecommerce.dto.Cumparare;
import com.ioansavulescu.ecommerce.dto.CumparareResponse;
import com.ioansavulescu.ecommerce.dto.PaymentInfo;
import com.ioansavulescu.ecommerce.entity.Client;
import com.ioansavulescu.ecommerce.entity.Comanda;
import com.ioansavulescu.ecommerce.entity.Detaliu_Comanda;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private ClientRepository clientRepository;

    @Autowired
    public CheckoutServiceImpl(ClientRepository clientRepository,
                               @Value("${stripe.key.secret}") String secretKey) {
        this.clientRepository = clientRepository;

        // initializare API Stripe cu secret key
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public CumparareResponse puneComanda(Cumparare cumparare) {

        // intoarce info despre comanda din dto
        Comanda comanda = cumparare.getComanda();

        // genereaza numar de urmarire
        String numarUrmarireComanda = genereazaNumarUrmarireComanda();
        comanda.setUrmarireNumarComanda(numarUrmarireComanda);

        // populeaza comanda cu detaliuComenzi
        Set<Detaliu_Comanda> detaliuComenzi = cumparare.getDetaliuComenzi();
        detaliuComenzi.forEach(item -> comanda.add(item));

        // populeza comanda cu adresaFacutrare si adresaLivrare
        comanda.setAdresaFacturare(cumparare.getAdresaFacturare());
        comanda.setAdresaLivrare(cumparare.getAdresaLivrare());

        // populeaza client cu comanda
        Client client = cumparare.getClient();

        // verificam daca avem deja acest client
        String theEmail = client.getEmail();

        Client clientDinDB = clientRepository.findByEmail(theEmail);

        if(clientDinDB != null) {
            // daca il gasim il potrivim
            client = clientDinDB;
        }

        client.add(comanda);

        // salvare in bd
        clientRepository.save(client);

        // intoarce un raspuns
        return new CumparareResponse(numarUrmarireComanda);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "bitShop - comanda");
        params.put("receipt_email", paymentInfo.getReceiptEmail());

        return PaymentIntent.create(params);
    }

    private String genereazaNumarUrmarireComanda() {

        // generam un UUID aleator
        return UUID.randomUUID().toString();

    }
}
