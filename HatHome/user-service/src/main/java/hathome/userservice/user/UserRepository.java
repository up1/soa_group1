package hathome.userservice.user;

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


    public boolean signup(User user){
        String sql = "INSERT INTO user (email, password, address) values ('"+ user.getEmail() +"', '"+ user.getPassword() +"', '"+ user.getAddress() +"');";
        try {
            this.jdbcTemplate.execute(sql);
            return true;

        }catch (Exception e){
            System.out.print(e);
            return false;
        }
    }

    @Transactional(readOnly = true)
    public User findById(Integer userId) {
        try {
            String sql = "SELECT email, address, id FROM USER WHERE id=?";
            return this.jdbcTemplate.queryForObject(sql,
                    new Object[]{userId}, new UserRowMapper());
        }catch (Exception exception) {
            System.out.println(">>>>>" + exception);
            throw new UserNotFoundException(userId);
        }
    }
}
