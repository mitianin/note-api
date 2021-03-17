package org.example.servlet.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.UberFactory;
import org.example.servlet.db.JdbcTemplate;
import org.example.servlet.dto.NotesResponse;

import java.io.IOException;

public class MyDeleteServlet extends JsonServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UberFactory uf = UberFactory.getInstance();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(uf.getDs());

        int idDelete = Integer.parseInt(req.getParameter("id"));
        NotesResponse notesResponse = new NotesResponse();

        jdbcTemplate.delete("delete from notes where id=?", new Object[]{idDelete});
        notesResponse.setStatus("OK");

        writeJson(notesResponse, resp);
    }
}
