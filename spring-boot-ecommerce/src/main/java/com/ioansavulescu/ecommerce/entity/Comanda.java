package com.ioansavulescu.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="comenzi")
@Setter
@Getter
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numar_urmarire_comanda")
    private String urmarireNumarComanda;

    @Column(name = "cantitate_totala")
    private int cantitateTotala;

    @Column(name = "pret_total")
    private BigDecimal pretTotal;

    @Column(name = "stare")
    private String stare;

    @Column(name = "data_creare")
    @CreationTimestamp
    private Date creareData;

    @Column(name = "ultima_actualizare")
    @UpdateTimestamp
    private Date ultima_actualizare;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comanda")
    private Set<Detaliu_Comanda> detaliuComenzi = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresa_livrare", referencedColumnName = "id")
    private Adresa adresaLivrare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresa_facturare", referencedColumnName = "id")
    private Adresa adresaFacturare;

    public void add(Detaliu_Comanda item) {

        if(item != null) {
            if(detaliuComenzi == null) {
                detaliuComenzi = new HashSet<>();
            }

            detaliuComenzi.add(item);
            item.setComanda(this);
        }
    }
}
