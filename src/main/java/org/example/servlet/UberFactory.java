package org.example.servlet;

import lombok.ToString;
import org.example.servlet.db.DataBaseLoginData;
import org.example.servlet.db.JdbcTemplate;
import org.example.servlet.db.MyDataSource;
import org.example.servlet.infrastructure.annotations.Autowired;
import org.example.servlet.infrastructure.annotations.Component;
import org.example.servlet.service.DataBaseNoteService;
import org.example.servlet.service.NoteService;

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
}
