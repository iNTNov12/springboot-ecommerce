package com.ioansavulescu.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="adresa")
@Getter
@Setter
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @Column(name="strada")
    private String strada;

    @Column(name="oras")
    private String oras;

    @Column(name="judet")
    private String state;

    @Column(name="tara")
    private String tara;

    @Column(name="cod_postal")
    private String codZip;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Comanda comanda;
}
