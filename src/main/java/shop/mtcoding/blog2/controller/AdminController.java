package shop.mtcoding.blog2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.admin.AdminReq.AdminReqDto;
import shop.mtcoding.blog2.dto.admin.AdminReq.AdminReqSearchAjaxDto;
import shop.mtcoding.blog2.dto.admin.AdminReq.AdminReqSearchReplyAjaxDto;
import shop.mtcoding.blog2.dto.admin.AdminResp.AdminBoardRespDto;
import shop.mtcoding.blog2.dto.admin.AdminResp.AdminBoardSearchResqDto;
import shop.mtcoding.blog2.dto.admin.AdminResp.AdminReplyRespDto;
import shop.mtcoding.blog2.dto.admin.AdminResp.AdminReplySearchRespDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.exception.CustomException;
import shop.mtcoding.blog2.model.BoardRepository;
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
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    public void setUp() {
        User mockUser = User.builder()
                        .id(1)
                        .username("admin")
                        .password("admin")
                        .role("ADMIN")
                        .email("admin@nate.com")
                        .build();
    }

    @GetMapping("/admin/loginForm")
    public String loginForm() {
        return "admin/loginForm";
    }

    @GetMapping("/admin")
    public String admin() {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/admin/loginForm";
        }
        if (!principal.getRole().equals("ADMIN")) {
            return "redirect:/admin/loginForm";
        }
        return "admin/user";
    }

    @GetMapping("/admin/user")
    public String manageUser(Model model) {
        User admin = (User) session.getAttribute("principal");
        if (admin == null) {
            return "redirect:/admin/loginForm";
        }
        if (!admin.getRole().equals("ADMIN")) {
            return "redirect:/admin/loginForm";
        }
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "admin/user";
    }

    @GetMapping("/admin/board")
    public String manageBoard(Model model) {
        setUp();
        User admin = (User) session.getAttribute("principal");
        if (admin == null) {
            return "redirect:/admin/loginForm";
        }
        if (!admin.getRole().equals("ADMIN")) {
            return "redirect:/admin/loginForm";
        }
        List<AdminBoardRespDto> boardList = boardRepository.findAllByAdmin();
        model.addAttribute("boardList", boardList);
        return "admin/board";
    }

    @GetMapping("/admin/reply")
    public String manageReply(Model model) {
        User admin = (User) session.getAttribute("principal");
        if (admin == null) {
            return "redirect:/admin/loginForm";
        }
        if (!admin.getRole().equals("ADMIN")) {
            return "redirect:/admin/loginForm";
        }
        List<AdminReplyRespDto> replyList = replyRepository.findAllByAdmin();
        model.addAttribute("replyList", replyList);
        return "/admin/reply";
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@Valid AdminReqDto adminReqDto, Error error, Model model) {
        User admin = userRepository.findByUsernameAndPassword(adminReqDto.getUsername(), adminReqDto.getPassword());
        if (admin == null) {
            throw new CustomException("아이디 또는 비밀번호가 다릅니다.");
        }
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomException("관리자 계정이 아닙니다.");
        }
        session.setAttribute("principal", admin);
        // 회원정보 가져오기
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/board/search")
    public ResponseEntity<?> searchBoard(AdminReqSearchAjaxDto aDto) {  
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("관리자 계정이 아닙니다.");
        }
        if (aDto.getTitle() == null || aDto.getTitle().isEmpty()) {
            aDto.setTitle("");
        }
        if (aDto.getContent() == null || aDto.getContent().isEmpty()) {
            aDto.setContent("");
        }
        if (aDto.getUsername() == null || aDto.getUsername().isEmpty()) {
            aDto.setUsername("");
        } else {
            Integer num = userRepository.findIdByUsernameWithAdmin(aDto.getUsername());
            System.out.println("테스트 : "+num);
            if (num == null) {
                aDto.setUsername("결과없음");
            } else {
                aDto.setUsername(String.valueOf(num));
            }
        }
        List<AdminBoardSearchResqDto> boardSeartList = boardRepository.findBoardByAdminWithSearch(
                aDto.getTitle(),
                aDto.getContent(),
                aDto.getUsername());
        // json 에 데이터 넣어줘야함
        return new ResponseEntity<>(new ResponseDto<>(1, "", boardSeartList),
                HttpStatus.OK);
    }

    @GetMapping("/admin/reply/search")
    public ResponseEntity<?> searchReply(AdminReqSearchReplyAjaxDto aDto) {
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("관리자 계정이 아닙니다.");
        }
        System.out.println("테스트 : "+aDto.getComment());
        System.out.println("테스트 : "+aDto.getUsername());
        if (aDto.getComment() == null || aDto.getComment().isEmpty()) {
            aDto.setComment("");
        }
        if (aDto.getUsername() == null || aDto.getUsername().isEmpty()) {
            aDto.setUsername("");
        } else {
            Integer num = userRepository.findIdByUsernameWithAdmin(aDto.getUsername());
            System.out.println("테스트 : "+num);
            if (num == null) {
                aDto.setUsername("결과없음");
            } else {
                aDto.setUsername(String.valueOf(num));
            }
        }
        List<AdminReplySearchRespDto> replySeartList = replyRepository.findReplyByAdminWithSearch(
                aDto.getComment(),
                aDto.getUsername());
        // json 에 데이터 넣어줘야함
        return new ResponseEntity<>(new ResponseDto<>(1, "", replySeartList),
                HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<?> delateUser(@PathVariable Integer id) {
        if (id == null) {
            throw new CustomApiException("삭제할 회원 아이디가 비었습니다.");
        }
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("관리자 계정이 아닙니다.");
        }
        userService.회원삭제(id, admin);
        return new ResponseEntity<>(new ResponseDto<>(1, "유저 계정 삭제 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/admin/board/{id}")
    public ResponseEntity<?> delateBoard(@PathVariable Integer id) {
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("관리자 계정이 아닙니다.");
        }
        if (id == null) {
            throw new CustomApiException("삭제할 게시글 아이디가 비었습니다.");
        }
        boardService.글삭제(id, admin.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 삭제 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/admin/reply/{id}")
    public ResponseEntity<?> delateReply(@PathVariable Integer id) {
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("관리자 계정이 아닙니다.");
        }
        if (id == null) {
            throw new CustomApiException("삭제할 댓글 아이디가 비었습니다.");
        }
        replyService.댓글삭제(id, admin.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "댓글 삭제 성공", null), HttpStatus.OK);
    }
}
