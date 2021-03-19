package org.example.servlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.servlet.UberFactory;
import org.example.servlet.dto.Note;
import org.example.servlet.dto.NotesResponse;
import org.example.servlet.service.NoteService;

import java.io.IOException;


public class MyServlet extends JsonServlet {
    UberFactory uf = UberFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        NoteService noteService = uf.getNoteService();

        NotesResponse notesResponse = noteService.getAll();
        req.setAttribute("notes", notesResponse.getNoteList());
        try {
            req.getRequestDispatcher("WEB-INF/views/notes.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Note note = new Note();
        note.setName(req.getParameter("noteName"));
        note.setTxt(req.getParameter("noteText"));

        NoteService noteService = uf.getNoteService();
        noteService.post(note);
        resp.sendRedirect("/");

    }
}
