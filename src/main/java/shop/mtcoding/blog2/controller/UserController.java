package shop.mtcoding.blog2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog2.dto.user.UserReq.UserJoinDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserLoginDto;
import shop.mtcoding.blog2.exception.CustomException;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.model.UserRepository;
import shop.mtcoding.blog2.service.UserService;

@Controller
public class UserController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/update")
    public String updateForm(){
        
    return "user/updateForm";
    }

    @GetMapping("/login")
    public String loginForm(){
        
    return "user/loginForm";
    }

    @GetMapping("/join")
    public String joinForm(){
        
    return "user/joinForm";
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
    return "redirect:/";
    }

    /// 오늘 수업
    @GetMapping("/user/profileUpdateForm")
    public String profileUpdateForm(){
        User principal = (User) session.getAttribute("principal");
        if( principal == null ){
           return "redirect:/loginForm";
        }


        return "user/updateForm";
    }

    @PostMapping("/join")
    public String userJoin(UserJoinDto userDto){
        if( userDto.getUsername()==null||userDto.getUsername().isEmpty()){
            throw new CustomException("아이디를 입력하세요");
        }
        if( userDto.getPassword()==null||userDto.getPassword().isEmpty()){
            throw new CustomException("패스워드를 입력하세요");
        }
        if( userDto.getEmail()==null||userDto.getEmail().isEmpty()){
            throw new CustomException("이메일을 입력하세요");
        }
        service.회원가입(userDto);        
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String userLogin(UserLoginDto userDto){
        if( userDto.getUsername()==null||userDto.getUsername().isEmpty()){
            throw new CustomException("아이디를 입력하세요");
        }
        if( userDto.getPassword()==null||userDto.getPassword().isEmpty()){
            throw new CustomException("패스워드를 입력하세요");
        }
        User prinipal = service.로그인(userDto);
        session.setAttribute("principal", prinipal);         
        return "redirect:/";
    }

    @PostMapping("/join2")
    public String userJoin2(){
        
        return "";
    }
}
