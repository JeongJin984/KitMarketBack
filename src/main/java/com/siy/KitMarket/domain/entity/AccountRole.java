package com.siy.KitMarket.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_ROLE")
@Data
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRole {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

