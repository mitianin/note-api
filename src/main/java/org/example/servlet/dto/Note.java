package org.example.servlet.dto;

import lombok.Data;

@Data
public class Note {
    private String id;
    private String name;
    private String txt;
    private String time;
}
