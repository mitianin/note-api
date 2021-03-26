package org.example.servlet.dto;


import lombok.Data;
import org.example.servlet.infrastructure.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class NotesResponse {
    private List<Note> noteList = new ArrayList<>();
    private String status;
}
