package org.example.servlet.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class MyDataSource {
    private final DataBaseLoginData data;

    private DataSource ds = null;

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
