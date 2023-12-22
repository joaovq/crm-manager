package br.com.joaovq.crm.manager.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_tb")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 30, unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole role = UserRole.USER;
    @Column(name = "user_active")
    private Boolean isActive = true;
    @Column(name = "user_email", length = 30, unique = true)
    private String email;

    public User(String username, String password, UserRole role, Boolean isActive, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ADMIN"),
                    new SimpleGrantedAuthority("USER")
            );
        }
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
