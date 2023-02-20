package shop.mtcoding.blog2.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.love.LoveReqDto.LoveBoardReqDto;
import shop.mtcoding.blog2.dto.love.LoveRespDto.LoveBoardRespDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.model.Love;
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
        // System.out.println("테스트인풋 : "+lDto);
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
        if ( lDto.getState() == 0 ){
            System.out.println("테스트 : 0 이기 때문에 1 으로 바꿉니다.");
            lDto.setState(1);
        }else{
            System.out.println("테스트 : 1 이기 때문에 0 으로 바꿉니다.");
            lDto.setState(0);
        }

        // System.out.println("테스트 :sdsds "+ lDto.getState());
        loveService.클릭하기(lDto, principal.getId());
        LoveBoardRespDto loveDto =  loveRepository.findByBoardIdAndUserId(lDto.getBoardId(), principal.getId());
        // System.out.println("테스트 zzzz: "+ loveDto.getState());
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", loveDto), HttpStatus.OK);
    }

    @DeleteMapping("/love/click")
    public ResponseEntity<?> cancel(@RequestBody LoveBoardReqDto lDto){
        // System.out.println("테스트인풋 : "+lDto);
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
        loveService.좋아요취소(lDto, principal.getId());
        LoveBoardRespDto loveDto =  loveRepository.findByBoardIdAndUserId(lDto.getBoardId(), principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", loveDto), HttpStatus.OK);
    }
}
