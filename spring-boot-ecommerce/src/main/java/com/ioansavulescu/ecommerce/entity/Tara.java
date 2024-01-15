package com.ioansavulescu.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tara")
@Getter
@Setter
public class Tara {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int ind;

    @Column(name = "cod")
    private String cod;

    @Column(name = "nume")
    private String nume;

    @OneToMany(mappedBy = "tara")
    @JsonIgnore
    private List<State> states;
}
