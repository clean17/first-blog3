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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.board.BoardReq.BoardUpdateDto;
import shop.mtcoding.blog2.dto.board.BoardReq.BoardWriteDto;
import shop.mtcoding.blog2.dto.board.BoardResp.BoardDetailDto;
import shop.mtcoding.blog2.dto.board.BoardResp.BoardMainListDto;
import shop.mtcoding.blog2.dto.board.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.blog2.dto.love.LoveRespDto.LoveBoardRespDto;
import shop.mtcoding.blog2.dto.reply.ReplyResp.ReplyListRespDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.exception.CustomException;
import shop.mtcoding.blog2.model.BoardRepository;
import shop.mtcoding.blog2.model.LoveRepository;
import shop.mtcoding.blog2.model.ReplyRepository;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private HttpSession session;

    @Autowired
    private BoardService service;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private LoveRepository loveRepository;

    private void mockSession() {
        User mockUser = User.builder()
                        .id(1)
                        .username("ssar")
                        .password("1234")
                        .email("ssar@nate.com")
                        .profile("/images/default_profile.png")
                        .build();
        session.setAttribute("principal", mockUser);
    }

    @GetMapping("/")
    public String main(Model model) {
        // mockSession();
        Integer num = null;
        User principal = (User) session.getAttribute("principal"); // ????????? ??????????????? null ????????? ????????? ????????? !!!
        if (principal != null) {
            num = principal.getId();
        }
        List<BoardMainListDto> dtos = boardRepository.findAllforList(num);
        model.addAttribute("dtos", dtos);
        return "board/main";
    }

    @GetMapping("/board/write")
    public String writeForm() {

        return "board/writeForm";
    }

    @GetMapping("/board/detail/{id}")
    public String boardDetail(@PathVariable int id, Model model){
        Integer num = null;
        User principal = (User) session.getAttribute("principal"); // ????????? ??????????????? null ????????? ????????? ????????? !!!
        if ( principal != null ){
            num = principal.getId();
        }
        BoardDetailDto db =  boardRepository.findBoardforDetail(id, num);
        model.addAttribute("dto", db);

        List<ReplyListRespDto> replyList = replyRepository.findByBoardIdWithUser(id);
        model.addAttribute("replyList", replyList);
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("???????????? ????????? ???????????????.", HttpStatus.UNAUTHORIZED);
        }
        BoardUpdateRespDto dto = boardRepository.findByIdforUpdate(id);
        if (dto == null) {
            throw new CustomException("???????????? ?????? ????????? ?????????.");
        }
        if (dto.getUserId() != principal.getId()) {
            throw new CustomException("?????? ???????????? ????????? ????????? ????????????.", HttpStatus.FORBIDDEN);
        }
        model.addAttribute("dto", dto);
        return "board/updateForm";
    }

    @PostMapping("/board/write")
    public String boardWrite(BoardWriteDto boardDto) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("???????????? ????????? ???????????????.", HttpStatus.UNAUTHORIZED);
        }
        if (boardDto.getTitle() == null || boardDto.getTitle().isEmpty()) {
            throw new CustomException("??? ????????? ????????????.");
        }
        if (boardDto.getTitle().length() > 100) {
            throw new CustomException("????????? 100??? ????????? ???????????????.");
        }
        if (boardDto.getContent() == null || boardDto.getContent().isEmpty()) {
            throw new CustomException("??? ????????? ????????????.");
        }
        service.?????????(boardDto, principal.getId());
        return "redirect:/";
    }

    @DeleteMapping("/board/{id}/delete")
    public ResponseEntity<?> boardDelete(@PathVariable int id) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("???????????? ????????? ???????????????.", HttpStatus.UNAUTHORIZED);
        }
        service.?????????(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "?????? ??????", null), HttpStatus.OK);
    }

    @PutMapping("/board/{id}")
    public ResponseEntity<?> boardUpdate(@PathVariable int id, @RequestBody BoardUpdateDto bDto) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("???????????? ????????? ???????????????.", HttpStatus.UNAUTHORIZED);
        }
        if (bDto.getTitle() == null || bDto.getTitle().isEmpty()) {
            throw new CustomException("??? ????????? ????????????.");
        }
        if (bDto.getTitle().length() > 100) {
            throw new CustomException("????????? 100??? ????????? ???????????????.");
        }
        if (bDto.getContent() == null || bDto.getContent().isEmpty()) {
            throw new CustomException("??? ????????? ????????????.");
        }
        service.?????????(bDto, id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "?????? ??????", true), HttpStatus.OK);
    }

}
