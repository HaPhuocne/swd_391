package com.fpt.swd391.fall2022.swd391.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "email is mandatory")
    private String name;
    private String address;

    private boolean status;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL)
    private Set<Product> products;

    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL)
    private Set<InventoryNote> inventoryNotes;

    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL)
    private Set<Order> orders;

}
