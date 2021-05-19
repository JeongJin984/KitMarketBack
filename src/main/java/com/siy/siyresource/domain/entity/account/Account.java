package com.siy.siyresource.domain.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    /*
     * 사용자 데이터
     * */

    @Column
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private int age;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    List<AccountRole> accountRoles = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<AccountPost> accountPosts = new ArrayList<>();



    public Account(String username, String password, String email, int age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAccountRoles(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
        this.accountRoles.stream().forEach(accountRole -> {
            accountRole.setAccount(this);
        });
    }
}
