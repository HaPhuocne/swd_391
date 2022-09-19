package com.fpt.swd391.fall2022.swd91.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emai;
    private String pasword;
    private String fullname;
    private int phone;
    private String address;
    private String image;
    private String role;
    private boolean status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
    private Shop shop;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
    private Set<WareHouse> wareHouses;
}
