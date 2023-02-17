package shop.mtcoding.blog2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.exception.CustomException;
import shop.mtcoding.blog2.model.Reply;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.service.ReplyService;

@Controller
public class ReplyController {
    
    @Autowired
    private HttpSession session;

    @Autowired
    private ReplyService replyService;

    // @Autowired
    // private ReplyRepository replyRepository;

    // @GetMapping("/")

    @PostMapping("/reply/save")
    public ResponseEntity<?> save(@RequestBody ReplySaveReqDto rdto){
        System.out.println("테스트 아이디: "+rdto.getUserId());
        System.out.println("테스트aaaaaaa : "+rdto.getUserId());
        User principal = (User) session.getAttribute("principal");
        if( principal ==null){
            throw new CustomApiException("인증이 되지 않았습니다.",HttpStatus.UNAUTHORIZED);
        }
        System.out.println("테스트bbbbbbbb : "+principal.getId());
        if( rdto.getComment()==null||rdto.getComment().isEmpty()){
            throw new CustomApiException("댓글을 작성해주세요");
        }
        
        if( rdto.getBoardId() == null){ // null 을 걸러야 함.. INTEGER 로 선언해
            throw new CustomApiException("게시글 번호가 필요합니다.");
        }
        System.out.println("테스트cccccccccccz : ");
        
        Integer returnPK = replyService.댓글쓰기(rdto, principal.getId());
        if ( returnPK == null ){
            returnPK = -1;
        }
        System.out.println("테스트ddddddddd : ");
        return new ResponseEntity<>(new ResponseDto<>(1, "댓글 쓰기 성공", returnPK), HttpStatus.OK);
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<?> deleteReply(@PathVariable int id){
        System.out.println("테스트 : "+ id);
        User principal = (User) session.getAttribute("principal");
        if( principal == null){
            throw new CustomApiException("인증이 되지 않았습니다.",HttpStatus.UNAUTHORIZED);
        }
        // if ( id == null ){
        //     throw new CustomApiException("게시글에 작성된 댓글만 삭제할 수 있습니다.");
        // }
        // 비교는 서비스에서 해야함 
        
        replyService.댓글삭제(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "삭제 성공", null), HttpStatus.OK);
    }
}
