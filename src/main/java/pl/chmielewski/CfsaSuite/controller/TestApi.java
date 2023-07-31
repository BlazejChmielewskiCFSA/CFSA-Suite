package pl.chmielewski.CfsaSuite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestApi {

    @GetMapping("forall")
    public String forAll(Principal principal){
        return "forAll" + principal.getName();
    }

    @GetMapping("foradmin")
    public String forAdmin(Principal principal){
        return "forAdmin" + principal.getName();
    }

    @GetMapping("forUser")
    public String forUser(Principal principal){
        return "forUser" + principal.getName();
    }
}
