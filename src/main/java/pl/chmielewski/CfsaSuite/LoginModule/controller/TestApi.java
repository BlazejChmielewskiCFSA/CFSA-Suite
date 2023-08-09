package pl.chmielewski.CfsaSuite.LoginModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestApi {

    @GetMapping("forall")
    public String forAll(){
        return "forAll";
    }

    @GetMapping("foradmin")
    public String forAdmin(){
        return "admin";
    }

    @GetMapping("home")
    public String forUser(){
        return "home";
    }
}
