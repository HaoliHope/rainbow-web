package org.rainbow.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api("首页")
@Controller
public class IndexController {

    @GetMapping("index")
    public String index() {
        return "index";
    }
}
