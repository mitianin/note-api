package org.example.servlet.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.example.servlet.infrastructure.annotations.Autowired;
import org.example.servlet.infrastructure.annotations.Component;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class MyDataSource {

    @Autowired
    private final DataBaseLoginData data;
    private DataSource ds;

    public DataSource createDataSource() {

        if (ds == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(data.getDsn());
            config.setUsername(data.getUser());
            config.setPassword(data.getPas());
            config.setMaximumPoolSize(8);
            config.setMinimumIdle(4);
            return new HikariDataSource(config);
        }
        return ds;
    }
}
