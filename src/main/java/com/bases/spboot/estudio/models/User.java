package com.bases.spboot.estudio.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;

    @Getter @Setter @Column(name = "fullName")
    private String fullName;

    @Getter @Setter @Column(name = "userName")
    private String userName;

    @Getter @Setter @Column(name = "password")
    private String password;

    @Getter @Setter @Column(name = "creatAt")
    private String creatAt;

}
