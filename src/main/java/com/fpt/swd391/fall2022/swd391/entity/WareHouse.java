package com.fpt.swd391.fall2022.swd391.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "wareHouse",cascade = CascadeType.ALL)
    private Set<SystemCategory> systemCategories;

    @OneToMany(mappedBy = "wareHouse",cascade = CascadeType.ALL)
    private Set<InventoryProduct> inventoryProducts;
}
