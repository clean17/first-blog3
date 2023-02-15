package shop.mtcoding.blog2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.admin.AdminReq.AdminReqDeleteDto;
import shop.mtcoding.blog2.dto.admin.AdminReq.AdminReqDto;
import shop.mtcoding.blog2.dto.admin.AdminResp.AdminBoardRespDto;
import shop.mtcoding.blog2.dto.admin.AdminResp.AdminReplyRespDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.exception.CustomException;
import shop.mtcoding.blog2.model.BoardRepository;
import shop.mtcoding.blog2.model.Reply;
import shop.mtcoding.blog2.model.ReplyRepository;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.model.UserRepository;
import shop.mtcoding.blog2.service.BoardService;
import shop.mtcoding.blog2.service.ReplyService;
import shop.mtcoding.blog2.service.UserService;

@Controller
public class AdminController {
    
    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private ReplyService replyService;

    @GetMapping("/admin/loginForm")
    public String loginForm(){
        
        return "admin/loginForm";
    }
    
    @GetMapping("/admin")
    public String admin(){
        User principal = (User)session.getAttribute("principal");
        if ( principal == null ){
            return "redirect:/admin/loginForm";
        }
        if ( !principal.getRole().equals("ADMIN")){
            return "redirect:/admin/loginForm";
        }
        return "admin/user";
    }

    @GetMapping("/admin/user")
    public String manageUser(Model model){
        User admin = (User)session.getAttribute("principal");
        if ( admin == null ){
            return "redirect:/admin/loginForm";
        }
        if ( !admin.getRole().equals("ADMIN")){
            return "redirect:/admin/loginForm";
        }
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
    return "admin/user";
    }

    @GetMapping("/admin/board")
    public String manageBoard(Model model){
        User admin = (User)session.getAttribute("principal");
        if ( admin == null ){
            return "redirect:/admin/loginForm";
        }
        if ( !admin.getRole().equals("ADMIN")){
            return "redirect:/admin/loginForm";
        }
        List<AdminBoardRespDto> boardList = boardRepository.findAllByAdmin();
        model.addAttribute("boardList", boardList);
    return "admin/board";
    }

    @GetMapping("/admin/reply")
    public String manageReply(Model model){
        User admin = (User)session.getAttribute("principal");
        if ( admin == null ){
            return "redirect:/admin/loginForm";
        }
        if ( !admin.getRole().equals("ADMIN")){
            return "redirect:/admin/loginForm";
        }
        List<AdminReplyRespDto> replyList = replyRepository.findAllByAdmin();
        model.addAttribute("replyList", replyList);
    return "admin/reply";
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

    @DeleteMapping("/admin/user/delete")
    public ResponseEntity<?> delateUser(@RequestBody AdminReqDeleteDto aDto){
        User admin = (User)session.getAttribute("principal");
        if( !admin.getRole().equals("ADMIN")){
            throw new CustomException("관리자 계정이 아닙니다.");
        }
        if ( aDto.getId() == null ){
            throw new CustomApiException("삭제할 회원 아이디가 비었습니다.");
        }
        userService.회원삭제(aDto.getId(), admin);
        return new ResponseEntity<>(new ResponseDto<>(1, "유저 계정 삭제 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/admin/board/delete")
    public ResponseEntity<?> delateBoard(@RequestBody AdminReqDeleteDto aDto){
        User admin = (User)session.getAttribute("principal");
        if( !admin.getRole().equals("ADMIN")){
            throw new CustomException("관리자 계정이 아닙니다.");
        }
        if ( aDto.getId() == null ){
            throw new CustomApiException("삭제할 게시글 아이디가 비었습니다.");
        }
        boardService.글삭제(aDto.getId(), admin.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 삭제 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/admin/reply/delete")
    public ResponseEntity<?> delateReply(@RequestBody AdminReqDeleteDto aDto){
        User admin = (User)session.getAttribute("principal");
        if( !admin.getRole().equals("ADMIN")){
            throw new CustomException("관리자 계정이 아닙니다.");
        }
        if ( aDto.getId() == null ){
            throw new CustomApiException("삭제할 댓글 아이디가 비었습니다.");
        }
        replyService.댓글삭제(aDto.getId(), admin.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "댓글 삭제 성공", null), HttpStatus.OK);
    }
}
