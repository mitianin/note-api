package org.example.servlet;

import org.example.servlet.db.DataBaseLoginData;
import org.example.servlet.db.JdbcTemplate;
import org.example.servlet.db.MyDataSource;
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
    private NoteService noteService;

    private UberFactory() {
        dbld = new DataBaseLoginData();
        mds = new MyDataSource(dbld);
        ds = mds.createDataSource();
        noteService = new DataBaseNoteService(new JdbcTemplate(ds));
    }

    public DataSource getDs() {
        return ds;
    }

    public NoteService getNoteService() {
        return noteService;
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
