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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;

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
    public String loginAdmin(AdminReqDto adminReqDto, Model model) {
        if (adminReqDto.getUsername() == null || adminReqDto.getUsername().isEmpty()) {
            throw new CustomException("???????????? ??????????????????");
        }
        if (adminReqDto.getPassword() == null || adminReqDto.getPassword().isEmpty()) {
            throw new CustomException("??????????????? ??????????????????");
        }
        User admin = userRepository.findByUsernameAndPassword(adminReqDto.getUsername(), adminReqDto.getPassword());
        if (admin == null) {
            throw new CustomException("????????? ?????? ??????????????? ????????????.");
        }
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomException("????????? ????????? ????????????.");
        }
        session.setAttribute("principal", admin);
        // ???????????? ????????????
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "redirect:/admin/user";
    }

    // @GetMapping("/admin/board/search")
    // public String searchBoard(AdminReqSearchDto aDto, Model model){
    //     User admin = (User)session.getAttribute("principal");
    //     if( !admin.getRole().equals("ADMIN")){
    //         throw new CustomException("????????? ????????? ????????????.");
    //     }
    //     // System.out.println("????????? ?????? : "+ aDto.getTitle());       
    //     // System.out.println("????????? ?????? : "+ aDto.getContent());       
    //     // System.out.println("????????? ????????? : "+ aDto.getUsername());
    //     if (aDto.getTitle()==null||aDto.getTitle().isEmpty()){
    //         aDto.setTitle("");
    //     }
    //     if (aDto.getContent()==null||aDto.getContent().isEmpty()){
    //         aDto.setContent("");
    //     }
    //     if (aDto.getUsername()==null||aDto.getUsername().isEmpty()){
    //         aDto.setUsername("");
    //     }else{
    //         Integer num = userRepository.findByUsernameWithAdmin(aDto.getUsername());
    //         // System.out.println("????????? ??????: "+num );
    //         if ( num == null ){
    //             aDto.setUsername("????????????");
    //         }else{
    //             aDto.setUsername(String.valueOf(num));
    //         }
    //     // }
    //     // System.out.println("????????? ??????: "+aDto.getTitle());
    //     // System.out.println("????????? ??????: "+aDto.getContent());
    //     // System.out.println("????????? ?????????: "+aDto.getUsername());
    //     // System.out.println("?????????-------------------------");
    //     // boardRepository.findAllByAdminWithSearch("","??????","");
    //     List<AdminBoardSearchResqDto> boardSeartList = boardRepository.findAllByAdminWithSearch(aDto.getTitle(), aDto.getContent(), aDto.getUsername());
    //     System.out.println("????????? : "+boardSeartList.size());
    //     model.addAttribute("boardList", boardSeartList);
    //     return "admin/board";
    // }

    @GetMapping("/admin/board/search")
    public ResponseEntity<?> searchBoard(AdminReqSearchAjaxDto aDto) {  
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("????????? ????????? ????????????.");
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
            Integer num = userRepository.findByUsernameWithAdmin(aDto.getUsername());
            System.out.println("????????? : "+num);
            if (num == null) {
                aDto.setUsername("????????????");
            } else {
                aDto.setUsername(String.valueOf(num));
            }
        }
        List<AdminBoardSearchResqDto> boardSeartList = boardRepository.findBoardByAdminWithSearch(
                aDto.getTitle(),
                aDto.getContent(),
                aDto.getUsername());
        // json ??? ????????? ???????????????
        return new ResponseEntity<>(new ResponseDto<>(1, "", boardSeartList),
                HttpStatus.OK);
    }

    @GetMapping("/admin/reply/search")
    public ResponseEntity<?> searchReply(AdminReqSearchReplyAjaxDto aDto) {
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("????????? ????????? ????????????.");
        }
        System.out.println("????????? : "+aDto.getComment());
        System.out.println("????????? : "+aDto.getUsername());
        if (aDto.getComment() == null || aDto.getComment().isEmpty()) {
            aDto.setComment("");
        }
        if (aDto.getUsername() == null || aDto.getUsername().isEmpty()) {
            aDto.setUsername("");
        } else {
            Integer num = userRepository.findByUsernameWithAdmin(aDto.getUsername());
            System.out.println("????????? : "+num);
            if (num == null) {
                aDto.setUsername("????????????");
            } else {
                aDto.setUsername(String.valueOf(num));
            }
        }
        List<AdminReplySearchRespDto> replySeartList = replyRepository.findReplyByAdminWithSearch(
                aDto.getComment(),
                aDto.getUsername());
        // json ??? ????????? ???????????????
        return new ResponseEntity<>(new ResponseDto<>(1, "", replySeartList),
                HttpStatus.OK);
    }

    @GetMapping("/test")
    @ResponseBody
    public String erew(){
        List<AdminReplySearchRespDto> replySeartList = replyRepository.findReplyByAdminWithSearch(
        "",
        "");
    return "replySeartList";
    }

    

    @DeleteMapping("/admin/user/{id}/delete")
    public ResponseEntity<?> delateUser(@PathVariable Integer id) {
        if (id == null) {
            throw new CustomApiException("????????? ?????? ???????????? ???????????????.");
        }
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("????????? ????????? ????????????.");
        }
        // userService.????????????(id, admin);
        return new ResponseEntity<>(new ResponseDto<>(1, "?????? ?????? ?????? ??????", null), HttpStatus.OK);
    }

    @DeleteMapping("/admin/board/{id}/delete")
    public ResponseEntity<?> delateBoard(@PathVariable Integer id) {
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("????????? ????????? ????????????.");
        }
        if (id == null) {
            throw new CustomApiException("????????? ????????? ???????????? ???????????????.");
        }
        boardService.?????????(id, admin.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "????????? ?????? ??????", null), HttpStatus.OK);
    }

    @DeleteMapping("/admin/reply/{id}/delete")
    public ResponseEntity<?> delateReply(@PathVariable Integer id) {
        User admin = (User) session.getAttribute("principal");
        if (!admin.getRole().equals("ADMIN")) {
            throw new CustomApiException("????????? ????????? ????????????.");
        }
        if (id == null) {
            throw new CustomApiException("????????? ?????? ???????????? ???????????????.");
        }
        replyService.????????????(id, admin.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "?????? ?????? ??????", null), HttpStatus.OK);
    }
}
