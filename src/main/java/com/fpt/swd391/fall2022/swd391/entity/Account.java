package com.fpt.swd391.fall2022.swd391.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Column(unique = true)
    @NotBlank(message = "email is mandatory")
    @Email
    private String email;
//^                 # start-of-string
//(?=.*[0-9])       # a digit must occur at least once
//(?=.*[a-z])       # a lower case letter must occur at least once
//(?=.*[A-Z])       # an upper case letter must occur at least once
//(?=.*[@#$%^&+=])  # a special character must occur at least once
//(?=\S+$)          # no whitespace allowed in the entire string
//.{8,}             # anything, at least eight places though
//$                 # end-of-string
    @NotBlank(message = "password is mandatory")
    @Length(max = 128)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;
    @NotBlank(message = "fullName is mandatory")
    private String fullName;
    @Max(10)
    private String phone;
    private String address;
    private String image;
    private boolean status;
    private float wallet;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
    private Shop shop;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
    private Set<WareHouse> wareHouses;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
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
