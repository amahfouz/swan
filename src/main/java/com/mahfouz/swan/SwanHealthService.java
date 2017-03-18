package com.mahfouz.swan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class SwanHealthService {
    
    @RequestMapping("/health")
    public SwanHealth health() {
        return new SwanHealth("UP", 123);
    }
}