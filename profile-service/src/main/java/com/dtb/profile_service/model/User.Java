package com.dtb.profile_service.model;
@Data
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String names;
    @Column(unique = true) private String email;
    private String gender;
    private String email;
    private String phone;
    private String password;
    private String role;
}