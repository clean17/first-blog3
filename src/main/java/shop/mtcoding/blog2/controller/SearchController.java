package shop.mtcoding.blog2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.blog2.dto.board.BoardResp.BoardSearchRespDto;
import shop.mtcoding.blog2.dto.search.SearchReq.SearchReqDto;
import shop.mtcoding.blog2.model.BoardRepository;
import shop.mtcoding.blog2.model.User;

@Controller
public class SearchController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private HttpSession session;

    @GetMapping("/search")
    public String search(Model model, SearchReqDto sDto) {
        Integer num = null;
        User principal = (User) session.getAttribute("principal"); // 세션에 오브젝트가 null 이라면 에러가 나온다 !!!
        if (principal != null) {
            num = principal.getId();
        }
        List<BoardSearchRespDto> dtos = boardRepository.findAllforSearch(num, sDto.getKeyword()); // num 동적쿼리
        model.addAttribute("dtos", dtos);
        return "board/search";
    }

}
