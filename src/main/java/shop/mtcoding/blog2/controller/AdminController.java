package shop.mtcoding.blog2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.blog2.model.User;

@Controller
public class AdminController {
    
    @Autowired
    private HttpSession session;

    @GetMapping("/admin")
    public String admin(){
        User principal = (User)session.getAttribute("principal");
        if ( principal == null ){
            return "admin/loginForm";
        }
        if ( !principal.getRole().equals("ADMIN")){
            return "admin/loginForm";
        }
    return "admin/user";
    }
}
