package org.example.servlet;

import lombok.Data;
import org.example.servlet.dto.Note;
import org.example.servlet.dto.NotesResponse;
import org.example.servlet.infrastructure.annotations.*;
import org.example.servlet.service.NoteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Data
public class MainController {
    @Autowired
    NoteService ns;


    @GetMapping("/notes")
    public void notes(HttpServletRequest req, HttpServletResponse resp) {

        NotesResponse notesResponse = ns.getAll();
        req.setAttribute("notes", notesResponse.getNoteList());
        try {
            req.getRequestDispatcher("/WEB-INF/views/notes.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/notes/add")
    public void add(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Note note = new Note();
        note.setName(req.getParameter("noteName"));
        note.setTxt(req.getParameter("noteText"));

        ns.post(note);
        response.sendRedirect("/notes");
    }

    @PostMapping("/notes/delete")
    public void delete(HttpServletRequest req, HttpServletResponse response) throws IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));

        ns.delete(idDelete);
        response.sendRedirect("/notes");
    }

}
