package hathome.authentication.authen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<User> findAllUser(){
        return this.jdbcTemplate.query("SELECT * FROM USER;", new UserRowMapper());
    }


    @Transactional(readOnly = true)
    public String login(String email, String password){
        String sql = "SELECT email, password FROM USER WHERE email = "+ email +" AND password = "+password+";";
        return "true";
    }

}
