package org.example.servlet.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.UberFactory;
import org.example.servlet.db.JdbcTemplate;
import org.example.servlet.dto.Note;
import org.example.servlet.dto.NotesResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class MyServlet extends JsonServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UberFactory uf = UberFactory.getInstance();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(uf.getDs());

        NotesResponse notesResponse = new NotesResponse();

        try {
            List<Note> list = jdbcTemplate.select("select id, name, txt, time from notes",
                    (rs) -> {
                        Note note = new Note();
                        try {
                            note.setId(rs.getString("id"));
                            note.setName(rs.getString("name"));
                            note.setTxt(rs.getString("txt"));
                            note.setTime(rs.getString("time"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                        return note;
                    });
            notesResponse.setNoteList(list);
            notesResponse.setStatus("OK");
        } catch (Exception e) {
            notesResponse.setStatus("ERROR");
        }

        writeJson(notesResponse, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Note note = readJson(Note.class, req);


        UberFactory uf = UberFactory.getInstance();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(uf.getDs());

        NotesResponse notesResponse = new NotesResponse();

        jdbcTemplate.insert("insert into notes (name, txt, time) values (?, ?, ?)",
                new Object[]{note.getName(), note.getTxt(), LocalDate.now()});

        notesResponse.setStatus("NOTE WAS CREATED");
        writeJson(notesResponse, resp);
    }
}
