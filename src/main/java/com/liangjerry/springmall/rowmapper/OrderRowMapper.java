package com.liangjerry.springmall.rowmapper;

import com.liangjerry.springmall.model.ProductOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<ProductOrder> {

    @Override
    public ProductOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductOrder order = new ProductOrder();
        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setTotalAmount(rs.getInt("total_amount"));
        order.setCreatedDate(rs.getTimestamp("created_date"));
        order.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return order;
    }
}
