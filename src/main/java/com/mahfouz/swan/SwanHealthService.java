package com.mahfouz.swan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@RestController
public final class SwanHealthService {
    
    @RequestMapping(value="/**", method=RequestMethod.POST)
    public String save(HttpServletRequest request) {
         String restOfTheUrl = (String) request.getAttribute
            (HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        return "PUT success:" + restOfTheUrl;
    }

    @RequestMapping(value="/**", method=RequestMethod.GET)
    public String get(HttpServletRequest request) {
         String restOfTheUrl = (String) request.getAttribute
            (HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        return "GET success:" + restOfTheUrl;
    }    

    @RequestMapping("/health")
    public SwanHealth health() {
        return new SwanHealth("UP", 123);
    }
}