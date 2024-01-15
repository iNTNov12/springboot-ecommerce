package com.ioansavulescu.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="produs")
@Data
public class Produs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name="id_categorie", nullable = false)
    private Categorie_Produs categorie;

    @Column(name = "unit_stoc")
    private String unit_stoc;

    @Column(name = "nume")
    private String nume;

    @Column(name = "descriere")
    private String descriere;

    @Column(name = "pret_unit")
    private BigDecimal pret_unit;

    @Column(name = "imagine_url")
    private String imagine_url;

    @Column(name = "activ")
    private boolean activ;

    @Column(name = "unitati_in_stoc")
    private int unitati_in_stoc;

    @Column(name = "data_creare")
    @CreationTimestamp
    private Date data_creare;

    @Column(name = "ultimul_update")
    @UpdateTimestamp
    private Date ultimul_update;
}
