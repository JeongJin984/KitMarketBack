package com.siy.KitMarket.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ROLE_RESOURCE")
@Data
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResource {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
