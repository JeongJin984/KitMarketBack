package com.siy.KitMarket.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "ACCOUNT")
@ToString(exclude = {"accountRoles"})
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    @Min(0)
    private int age;

    @Column
    private String password;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    Set<AccountRole> accountRoles = new HashSet<>();
}
