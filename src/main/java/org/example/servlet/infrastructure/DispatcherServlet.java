package org.example.servlet.infrastructure;

import org.example.servlet.infrastructure.annotations.*;
import org.example.servlet.infrastructure.reflection.PackageScanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


public class DispatcherServlet extends HttpServlet {

    private final PackageScanner ps = new PackageScanner();
    private final ApplicationContext applicationContext = new ApplicationContext();
    private final List<Class> controllers;

    public DispatcherServlet() {
        this.controllers = ps.findClassesWithAnn(Controller.class, "org.example.servlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        for (Class c : controllers) {
            for (Method m : c.getDeclaredMethods()) {

                String address = getAddress(req, m);

                if (address.equals("")) continue;

                if (address.equalsIgnoreCase(req.getRequestURI())) {

                    Object instance = applicationContext.getBeanByType(c);
                    try {
                        m.invoke(instance, req, resp);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String getAddress(HttpServletRequest req, Method m) {

        String address = "";
        if (req.getMethod().equalsIgnoreCase("get") &&
                m.isAnnotationPresent(GetMapping.class)) {
            address = m.getAnnotation(GetMapping.class).value();
        } else if (req.getMethod().equalsIgnoreCase("post") &&
                m.isAnnotationPresent(PostMapping.class)) {
            address = m.getAnnotation(PostMapping.class).value();
        } else if (req.getMethod().equalsIgnoreCase("put") &&
                m.isAnnotationPresent(PutMapping.class)) {
            address = m.getAnnotation(PutMapping.class).value();
        } else if (req.getMethod().equalsIgnoreCase("delete") &&
                m.isAnnotationPresent(DeleteMapping.class)) {
            address = m.getAnnotation(DeleteMapping.class).value();
        }
        return address;
    }


}
