package org.example.servlet.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.servlet.infrastructure.annotations.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    protected void writeJson(Object o, HttpServletResponse response) throws IOException {
        try {
            String str = objectMapper.writeValueAsString(o);

            response.setHeader("Content-Type", "application/json");
            response.getWriter().print(str);
            response.getWriter().flush();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    protected <T> T readJson(Class<T> clazz, HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getInputStream(), clazz);
    }

}
