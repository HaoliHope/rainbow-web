package org.rainbow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    // @PreAuthorize("hasAuthority('test:view')")
    @GetMapping("/test1")
    public String test1(){
        return "1";
    }

//    @PreAuthorize("hasAuthority('sys:user:edit')")
    @GetMapping("/test2")
    public String test2(){
        return "2";
    }

    // @PreAuthorize("hasAuthority('sys:user:add')")
    @GetMapping("/test3")
    public String test3(){
        return "3";
    }

    @GetMapping("403")
    public String test4(){
        return "error/403";
    }
}
