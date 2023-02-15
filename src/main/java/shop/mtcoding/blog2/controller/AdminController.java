package shop.mtcoding.blog2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog2.dto.admin.AdminReq.AdminReqDto;
import shop.mtcoding.blog2.exception.CustomException;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.model.UserRepository;

@Controller
public class AdminController {
    
    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/admin/login")
    public String LoginAdmin(AdminReqDto adminReqDto, Model model){
        if (adminReqDto.getUsername() == null || adminReqDto.getUsername().isEmpty()){
            throw new CustomException("아이디를 입력해주세요");
        }
        if (adminReqDto.getPassword() == null || adminReqDto.getPassword().isEmpty()){
            throw new CustomException("패스워드를 입력해주세요");
        }
        User admin = userRepository.findByUsernameAndPassword(adminReqDto.getUsername(), adminReqDto.getPassword());
        if( !admin.getRole().equals("ADMIN")){
            throw new CustomException("관리자 계정이 아닙니다.");
        }
        session.setAttribute("principal", admin);

        // 회원정보 가져오기
        List<User> userList = userRepository.findAll();

        model.addAttribute("userList", userList);
        return "admin/user";
    }
}
