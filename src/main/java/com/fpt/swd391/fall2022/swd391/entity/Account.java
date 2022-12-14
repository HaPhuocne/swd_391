package com.fpt.swd391.fall2022.swd391.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    private String password;
    private String fullName;
    private String phone;
    private String address;
    @Column(length = 5000)
    private String image;
    private boolean status;
    private float wallet;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Shop shop;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<WareHouse> wareHouses;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Order> order;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<Cart> cartItemCollection;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
