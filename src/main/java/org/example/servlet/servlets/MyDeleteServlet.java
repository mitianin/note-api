package org.example.servlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.servlet.UberFactory;
import org.example.servlet.db.JdbcTemplate;
import org.example.servlet.dto.NotesResponse;
import org.example.servlet.service.NoteService;

import java.io.IOException;

public class MyDeleteServlet extends JsonServlet {
    UberFactory uf = UberFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        NoteService noteService = uf.getNoteService();

        int idDelete = Integer.parseInt(req.getParameter("id"));

        noteService.delete(idDelete);
        resp.sendRedirect("/");
    }
}
