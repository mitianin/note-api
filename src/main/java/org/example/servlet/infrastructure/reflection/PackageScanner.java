package org.example.servlet.infrastructure.reflection;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackageScanner {

    public List<Class> classes(String pack) {
        List<Class> res = new ArrayList<>();
        String path = pack.replace(".", "/");
        try {
            URL url = getClass().getClassLoader().getResource(path);
            File file = new File(url.toURI());
            res = findClasses(file, "org.example.servlet");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return res;
    }

    private List<Class> findClasses(File file, String prefix) {

        List<Class> res = new ArrayList<>();

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                res.addAll(findClasses(f, prefix + "." + f.getName()));
            } else if (f.isFile() && f.getName().endsWith(".class")) {
                String filePath = f.getName();

                try {
                    Class clazz =
                            Class.forName(prefix + "." + filePath.substring(0, filePath.lastIndexOf(".class")));
                    res.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    public List<Class> findClassesWithAnn(Class ann, String packName){
        return classes(packName)
                .stream()
                .filter(c -> c.isAnnotationPresent(ann))
                .collect(Collectors.toList());
    }
}
