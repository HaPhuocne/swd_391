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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 2000)
    private String deception;
    private int quantity;
    private int price;
    private String size;
    private String color;
    private String URL;
    private boolean status;

    @Column(columnDefinition = "varchar(5000)", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "system_category_id")
    private SystemCategory systemCategory;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<InventoryProduct> inventoryProducts;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
