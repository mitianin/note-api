package org.example.servlet.db;


import lombok.Getter;
import lombok.ToString;
import org.example.servlet.infrastructure.annotations.Component;

@Getter
@Component
@ToString
public class DataBaseLoginData {
    private final String dsn = "jdbc:postgresql://localhost:5432/mydatabase";
    private final String user = "postgres";
    private final String pas = "1111";
}
