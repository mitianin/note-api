package org.example.servlet.servlets;

import org.example.servlet.UberFactory;
import org.example.servlet.dto.NotesResponse;
import org.example.servlet.service.NoteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyGetUserServlet extends JsonServlet {
    UberFactory uf = UberFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NoteService noteService = uf.getNoteService();

        String strId = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        int id = Integer.parseInt(strId.trim());
        NotesResponse notesResponse = noteService.getById(id);
//        writeJson(notesResponse, resp);

        req.setAttribute("note", notesResponse.getNoteList().get(0));
        try {
            getServletContext().getRequestDispatcher("/WEB-INF/views/mynote.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
