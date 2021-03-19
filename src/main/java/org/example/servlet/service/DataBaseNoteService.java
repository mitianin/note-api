package org.example.servlet.service;

import lombok.RequiredArgsConstructor;
import org.example.servlet.db.JdbcTemplate;
import org.example.servlet.dto.Note;
import org.example.servlet.dto.NotesResponse;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class DataBaseNoteService implements NoteService {
    private final JdbcTemplate jdbcTemplate;


    @Override
    public NotesResponse post(Note note) {
        NotesResponse notesResponse = new NotesResponse();

        jdbcTemplate.insert("insert into notes (name, txt, time) values (?, ?, ?)",
                new Object[]{note.getName(), note.getTxt(), LocalDate.now()});

        notesResponse.setStatus("NOTE WAS CREATED");

        return notesResponse;
    }

    @Override
    public NotesResponse getAll() {
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

        return notesResponse;
    }

    @Override
    public NotesResponse delete(int idDelete) {

        NotesResponse notesResponse = new NotesResponse();

        jdbcTemplate.delete("delete from notes where id=?", new Object[]{idDelete});
        notesResponse.setStatus("OK");

        return notesResponse;
    }

    @Override
    public NotesResponse getById(int id) {
        NotesResponse notesResponse = getAll();
        List<Note> list = notesResponse.getNoteList()
                .stream()
                .filter(note -> (note.getId()).equals(String.valueOf(id)))
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            notesResponse.setStatus("ERROR. NO SUCH USER WITH THAT ID");
            notesResponse.getNoteList().clear();
            return notesResponse;
        } else {
            notesResponse.setNoteList(list);
        }

        return notesResponse;
    }
}
