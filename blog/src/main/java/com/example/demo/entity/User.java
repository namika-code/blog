// 20250707 add
// ユーザー情報を保持するクラス

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Spring Securityのデフォルトを使う場合はこの名前が好ましい
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role = "ROLE_USER";

    // Getter / Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
