package org.example.servlet.service;

import org.example.servlet.dto.Note;
import org.example.servlet.dto.NotesResponse;

public interface NoteService {
    NotesResponse post(Note note);
    NotesResponse getAll();
    NotesResponse delete(int id);
    NotesResponse getById(int id);
}
