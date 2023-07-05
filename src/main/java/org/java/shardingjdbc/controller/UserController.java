package org.java.shardingjdbc.controller;

import org.java.shardingjdbc.pos.RequestSingleParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login(String username, String password) {
        return "123";
    }

    @GetMapping("/abc")
    public String abc(String username, String password) {
        try {
            return "index";
        } catch (Exception e) {
            return "login";
        }
    }

    @PostMapping(value = "/getSingleParam")
    public String getSingleParam(@RequestSingleParam(value = "name") String name) {
        System.out.println(name);
        return name;
    }

}
