package pl.chmielewski.CfsaSuite.LoginModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class TestApi {

    @GetMapping("forall")
    public String forAll(){
        return "forAll";
    }

    @GetMapping("foradmin")
    public String forAdmin(){
        return "forAdmin";
    }

    @GetMapping("home")
    public String forUser(){
        return "home";
    }
}
