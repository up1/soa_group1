package hathome.product.product;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setDetail(resultSet.getString("detail"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setColor(resultSet.getString("color"));
        product.setBrand(resultSet.getString("brand"));
        product.setAmount(resultSet.getInt("amount"));
        product.setType(resultSet.getString("type"));
        return product;
    }
}