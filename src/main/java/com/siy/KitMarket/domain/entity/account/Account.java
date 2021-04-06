package com.siy.KitMarket.domain.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
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
    List<AccountRole> accountRoles = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<AccountPost> accountPosts = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    @JsonIgnore
    List<Post> createdPost = new ArrayList<>();


    public Account(String username, String password, String email, @Min(0) int age) {
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
