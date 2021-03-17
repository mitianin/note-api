package org.example.servlet.db;


import lombok.Getter;

@Getter
public class DataBaseLoginData {
    private final String dsn = "jdbc:postgresql://localhost:5432/mydatabase";
    private final String user = "postgres";
    private final String pas = "1111";
}
