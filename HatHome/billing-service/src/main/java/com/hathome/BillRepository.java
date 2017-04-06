package com.hathome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Acer on 6/4/2560.
 */

@Repository
public class BillRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public BillRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public Bill findById(long id){
        String sql = "SELECT id, status, address, cart_id, total FROM BILLS WHERE id=?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new BillRowMapper());
    }
}
