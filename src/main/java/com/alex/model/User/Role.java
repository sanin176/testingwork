package com.alex.model.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idRole;
    @NotNull
    private String roleName;
    @NotNull
    private String displayName;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToMany(mappedBy="roleId", fetch = FetchType.EAGER)
    private List<User> users;
}
