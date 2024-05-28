package org.learning.spring.ioc.bean.scope.web.controller;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private User user;

    @GetMapping("/index.html")
    public String index(Model model) {
        model.addAttribute("user", user);
        return "index";
    }
}
