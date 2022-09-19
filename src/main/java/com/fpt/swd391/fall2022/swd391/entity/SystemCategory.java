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
@NoArgsConstructor
@AllArgsConstructor
public class SystemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean status;

    private int rank;

    @ManyToOne
    @JoinColumn(name = "ware_house_id")
    private WareHouse wareHouse;

    @OneToMany(mappedBy = "systemCategory",cascade = CascadeType.ALL)
    private Set<Product> products;
}
