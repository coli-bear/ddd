package com.study.contextmapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UpstreamContext {

    @GetMapping
    public UpstreamResponse upstreamAPI() {
        return new UpstreamResponse("data", "source");
    }
}
