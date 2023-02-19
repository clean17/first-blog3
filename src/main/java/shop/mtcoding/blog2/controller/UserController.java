package shop.mtcoding.blog2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserJoinDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserLoginDto;
import shop.mtcoding.blog2.exception.CustomApiException;
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
           return "redirect:/login";
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
        // System.out.println("테스트 : "+ prinipal.getProfile());
        session.setAttribute("principal", prinipal);         
        return "redirect:/";
    }

    @PutMapping("/user/profileUpdate")
    public ResponseEntity<?> profileUpdate(MultipartFile profile) throws Exception{ 
        User principal = (User) session.getAttribute("principal");
        if( principal == null ){
            throw new CustomApiException("로그인이 필요한 페이지 입니다.", HttpStatus.UNAUTHORIZED);
        }
        // System.out.println(profile.getContentType());
        // System.out.println(profile.getOriginalFilename());
        if( profile.isEmpty()){
            throw new CustomApiException("사진이 전송 되지 않았습니다.");
        }

      

        User userPS = service.프로필사진수정(profile, principal.getId());
        session.setAttribute("principal", userPS);
        System.out.println("테스트 : "+userPS.getProfile());
        return new ResponseEntity<>(new ResponseDto<>(1, "   성공", null), HttpStatus.OK);
    }
}
