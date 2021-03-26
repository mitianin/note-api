package org.example.servlet.dto;

import lombok.Data;
import org.example.servlet.infrastructure.annotations.Component;

@Data
public class Note {
    private String id;
    private String name;
    private String txt;
    private String time;
}
