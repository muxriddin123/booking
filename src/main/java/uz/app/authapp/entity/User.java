package uz.app.authapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String role;
//    List<String> auth;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String s : auth) {
//            authorities.add(new SimpleGrantedAuthority(s));
//        }

        authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        return authorities;
    }
}
