package shop.mtcoding.blog2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog2.Util.CheckValid;
import shop.mtcoding.blog2.Util.Script;
import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserJoinDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserLoginDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserUpdateReqDto;
import shop.mtcoding.blog2.handler.aop.LoginUser;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final HttpSession session;
    private final UserService service;

    @GetMapping("/update")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/user/profileUpdateForm")
    public String profileUpdateForm(@LoginUser User principal, Model model) {
        model.addAttribute("user", principal);
        return "user/profileUpdateForm";
    }

    @GetMapping("/user/usernameSameCheck")
    public @ResponseBody ResponseDto<?> inNull(String username) {
        CheckValid.inNull(username, "username을 입력해주세요");
        CheckValid.inNull(username, "동일한 username이 존재");
        return service.중복체크(username);
    }

    @PostMapping("/join")
    public String userJoin(@Validated UserJoinDto userDto) {
        CheckValid.inNull(userDto.getUsername(), "아이디를 입력하세요");
        CheckValid.inNull(userDto.getPassword(), "패스워드를 입력하세요");
        CheckValid.inNull(userDto.getEmail(), "이메일을 입력하세요");
        service.회원가입(userDto);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String userLogin(UserLoginDto userDto) {
        CheckValid.inNull(userDto.getUsername(), "아이디를 입력하세요");
        CheckValid.inNull(userDto.getPassword(), "패스워드를 입력하세요");
        User userPS = service.로그인(userDto);
        session.setAttribute("principal", userPS);
        return "redirect:/";
    }

    @PostMapping("/user/update")
    public @ResponseBody String update(UserUpdateReqDto updateReqDto) {
        CheckValid.inNullApi(principal(), "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        User principalPs = service.회원수정(updateReqDto, principal().getId());
        session.setAttribute("principal", principalPs);
        return Script.href("수정완료", "/");
    }

    @PutMapping("/user/profileUpdate")
    public ResponseEntity<?> profileUpdate(MultipartFile profile) throws Exception {
        CheckValid.inNullApi(principal(), "로그인이 필요한 페이지 입니다.", HttpStatus.UNAUTHORIZED);
        CheckValid.inNullApi(profile, "사진이 전송 되지 않았습니다.");
        User userPS = service.프로필사진수정(profile, principal().getId());
        session.setAttribute("principal", userPS);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", null), HttpStatus.OK);
    }

    public User principal() {
        return (User) session.getAttribute("principal");
    }
}
