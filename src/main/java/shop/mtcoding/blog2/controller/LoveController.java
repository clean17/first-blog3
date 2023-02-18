package shop.mtcoding.blog2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.love.LoveReqDto.LoveBoardReqDto;
import shop.mtcoding.blog2.dto.love.LoveRespDto.LoveBoardRespDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.model.LoveRepository;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.service.LoveService;

@Controller
public class LoveController {
    @Autowired
    private LoveRepository loveRepository;

    @Autowired
    private HttpSession session;
    
    @Autowired
    private LoveService loveService;
    
    @PostMapping("/love/click")
    public ResponseEntity<?> loveClick(@RequestBody LoveBoardReqDto lDto){
        User principal = (User) session.getAttribute("principal");
        if( principal == null ){
            throw new CustomApiException("로그인이 필요한 기능입니다.", HttpStatus.UNAUTHORIZED);
        }
        if( lDto.getBoardId() == null ){
            throw new CustomApiException("게시글 아이디가 필요합니다.");
        }
        if( lDto.getUserId() == null ){
            throw new CustomApiException("회원 아이디가 필요합니다.");
        }
        
        loveService.클릭하기(lDto, principal.getId());
        LoveBoardRespDto loveDto =  loveRepository.findByBoardIdAndUserId(lDto.getBoardId(), principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", loveDto), HttpStatus.OK);
    }
}
