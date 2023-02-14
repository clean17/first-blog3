package shop.mtcoding.blog2.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
    public String profileUpdateForm(Model model){
        User principal = (User) session.getAttribute("principal");
        if( principal == null ){
           return "redirect:/loginForm";
        }
        User userPS = userRepository.findById(principal.getId());
        model.addAttribute("user", userPS);
        return "user/profileUpdateForm";
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

    @PostMapping("/user/profileUpdate")
    public String profileUpdate(MultipartFile profile) throws Exception{ 
        System.out.println(profile.getContentType());
        System.out.println(profile.getSize());
        System.out.println(profile.getOriginalFilename());

        if( profile.isEmpty()){
            throw new CustomException("사진이 전송 되지 않았습니다.");
        }
        
        // 파일은 하드에 저장
        // Path imageFilePath = Paths.get("/images/"+profile.getOriginalFilename()); 
        Path imageFilePath = Paths.get("C:\\workspace\\project_lab\\blog2\\src\\main\\resources\\static\\images\\"); 
        System.out.println(imageFilePath);  // \images\logo192.png
//\images\dora.png
        try {
            Files.write(imageFilePath, profile.getBytes());
        } catch (Exception e) {
            e.getMessage();// TODO: handle exception
        }

        // 파일의 경로를 dB 에 저장

        return "redirect:/";
    }

    @PostMapping("/join2")
    public String userJoin2(){
        
        return "";
    }
}
