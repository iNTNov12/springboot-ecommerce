package com.ioansavulescu.ecommerce.dto;

import com.ioansavulescu.ecommerce.entity.Adresa;
import com.ioansavulescu.ecommerce.entity.Client;
import com.ioansavulescu.ecommerce.entity.Comanda;
import com.ioansavulescu.ecommerce.entity.Detaliu_Comanda;
import lombok.Data;

import java.util.Set;

@Data
public class Cumparare {

    private Client client;
    private Adresa adresaLivrare;
    private Adresa adresaFacturare;
    private Comanda comanda;
    private Set<Detaliu_Comanda> detaliuComenzi;
}
