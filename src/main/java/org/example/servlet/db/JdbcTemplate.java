package org.example.servlet.db;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JdbcTemplate {
    private final DataSource ds;

    public <T> List<T> select(String sql, Object[] params, RowMapper<T> mapper) {
        List<T> result = new ArrayList<>();

        ResultSet rs = null;
        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            while (rs.next()) {
                T o = mapper.map(rs);
                result.add(o);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public <T> List<T> select(String sql, RowMapper<T> mapper) {
        return select(sql, new Object[]{}, mapper);
    }

    public <T> T selectOne(String sql, Object[] params, RowMapper<T> mapper) {
        return select(sql, params, mapper).get(0);
    }

    public <T> T selectOne(String sql, RowMapper<T> mapper) {
        return selectOne(sql, new Object[]{}, mapper);
    }

    public void update(String sql, Object[] params) {
        execute(sql, params);
    }

    public void update(String sql) {
        update(sql, new Object[]{});
    }

    public void delete(String sql, Object[] params) {
        execute(sql, params);
    }

    public void delete(String sql) {
        delete(sql, new Object[]{});
    }

    public void insert(String sql, Object[] params) {
        execute(sql, params);
    }

    public void insert(String sql) {
        insert(sql, new Object[]{});
    }

    private void execute(String sql, Object[] params) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
