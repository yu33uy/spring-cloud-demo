package spring.cloud.auth.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.cloud.commons.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Frank on 2017/6/11.
 */
@Entity
@Table(name = "`user`")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username")
    @Setter
    private String username;

    @Column(name = "password")
    @Setter
    private String password;

    @Getter
    @Setter
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> auths = new ArrayList<>();
        for (UserRole userRole : this.userRoles) {
            auths.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }
        return auths;
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
        return true;
    }
}
