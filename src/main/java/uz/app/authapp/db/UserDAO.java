package uz.app.authapp.db;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import uz.app.authapp.entity.User;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserDAO {
    final NamedParameterJdbcTemplate jdbcTemplate;
    public void save(User user) {
        jdbcTemplate.update(
                "insert into users(username, password, role) values( :username, :password, :role) ",
                Map.of("username",user.getUsername(),
                        "password",user.getPassword(),
                        "role",user.getRole())
                );
    }


    public User getUserByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from users where username = :username", Map.of("username", username), (rs, rn) -> {
//            rs.next();
            User temp = new User();
            temp.setRole(rs.getString("role"));
            temp.setUsername(rs.getString("username"));
            temp.setPassword(rs.getString("password"));
            temp.setId(rs.getInt("id"));
            return temp;
        });
    }
}
