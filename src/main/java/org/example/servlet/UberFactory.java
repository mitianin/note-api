package org.example.servlet;

import org.example.servlet.db.DataBaseLoginData;
import org.example.servlet.db.MyDataSource;

import javax.sql.DataSource;

public class UberFactory {
    private static UberFactory INSTANCE = new UberFactory();

    public static UberFactory getInstance() {
        return INSTANCE;
    }

    private DataBaseLoginData dbld;
    private MyDataSource mds;
    private DataSource ds;

    private UberFactory() {
        dbld = new DataBaseLoginData();
        mds = new MyDataSource(dbld);
        ds = mds.createDataSource();
    }

    public DataSource getDs() {
        return ds;
    }

//        public void createTable() {
//        try (Connection connection = ds.getConnection()) {
//            PreparedStatement ps = connection.prepareStatement("create table if not exists " +
//                    "notes (id serial primary key, name varchar unique not null, note_txt text)");
//            ps.execute();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
