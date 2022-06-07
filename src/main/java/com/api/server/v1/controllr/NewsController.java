package com.api.server.v1.controllr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    @GetMapping("/news")
    public String selectNews() {
        return "news";
    }
}
