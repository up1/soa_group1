package hathome.authentication.authen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public User getUserByEmailAndPassword(String email, String password){
        User user = null;
        String sql = "SELECT id, email, password, address FROM USER WHERE email = '"+ email +"' AND password = '"+password+"';";
        try {
            user  = this.jdbcTemplate.queryForObject(sql, new Object[]{}, new UserRowMapper());
            return user;
        }catch (Exception e){
            System.out.println(e);
        }
        return user;
    }

}
