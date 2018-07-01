package com.cercle.jds.user.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    @ResponseBody
    public String register() {
        return "ok";
    }

}
