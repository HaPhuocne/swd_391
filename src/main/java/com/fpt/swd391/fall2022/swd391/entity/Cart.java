package com.fpt.swd391.fall2022.swd391.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private Long idCart;

    private int quantity;

    private int price;

    private int size;

    private String color;

    private String URL;

    @JoinColumn(name = "id_account")
    @ManyToOne(optional = false)
    private Account account;

    @JoinColumn(name = "id_product")
    @ManyToOne(optional = false)
    private Product product;



}