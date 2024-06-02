package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
}