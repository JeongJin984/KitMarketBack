package com.siy.KitMarket.domain.entity.account;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    @Min(0)
    private int age;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    Set<AccountRole> accountRoles = new HashSet<>();

    public Account(String username, String password, String email, @Min(0) int age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public void setAccountRoles(Set<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
        this.accountRoles.stream().forEach(accountRole -> {
            accountRole.setAccount(this);
        });
    }
}
