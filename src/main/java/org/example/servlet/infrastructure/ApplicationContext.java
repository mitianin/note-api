package org.example.servlet.infrastructure;

import org.example.servlet.infrastructure.annotations.Autowired;
import org.example.servlet.infrastructure.annotations.Component;
import org.example.servlet.infrastructure.annotations.Controller;
import org.example.servlet.infrastructure.reflection.PackageScanner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationContext {
    Map<Class, Object> beans = new HashMap<>();
    private final PackageScanner ps = new PackageScanner();

    public ApplicationContext() {
        createBeans();
    }

    private void createBeans() {
        List<Class> componentClasses = getComponentClasses();
        for (Class componentClass : componentClasses) {
            createBean(componentClass);
        }
        for (Class componentClass : componentClasses) {
            postProcessBean(beans.get(componentClass));
        }
    }

    private void postProcessBean(Object bean) {
        List<Field> fields = Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Autowired.class))
                .collect(Collectors.toList());

        for (Field field : fields) {
            field.setAccessible(true);

            Class type = field.getType();

            Object value = getBeanByType(type);
            try {
                field.set(bean, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getBeanByType(Class type) {
        Object value = beans.keySet().stream()
                .filter(type::isAssignableFrom)
                .findFirst()
                .map(c -> beans.get(c))
                .orElse(null);
        return value;
    }

    private List<Class> getComponentClasses() {
        String pack = "org.example.servlet";
        List<Class> listComponent = ps.findClassesWithAnn(Component.class, pack);
        List<Class> listController = ps.findClassesWithAnn(Controller.class, pack);

        return Stream.concat(listComponent.stream(), listController.stream())
                .collect(Collectors.toList());
    }

    private void createBean(Class componentClass) {
        try {
            Object o = componentClass.getConstructor().newInstance();
            beans.put(componentClass, o);
        } catch (Exception e) {
            throw new RuntimeException("ERROR WITH CREATING BEAN " + componentClass.getSimpleName(), e);
        }
    }
}
