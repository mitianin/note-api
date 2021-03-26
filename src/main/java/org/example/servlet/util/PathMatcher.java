package org.example.servlet.util;

import lombok.Data;
import org.example.servlet.infrastructure.annotations.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class PathMatcher {
    private Map<String, String> pathMap = new HashMap<>();

//    private boolean isMatches(){
//
//    }
}
