package shop.mtcoding.blog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.blog2.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.model.Reply;
import shop.mtcoding.blog2.model.ReplyRepository;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.model.UserRepository;

@Slf4j
@Transactional(readOnly = true)
@Service
public class ReplyService {
    
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Integer 댓글쓰기(ReplySaveReqDto rDto, int principalId){
        System.out.println("테스트 아이디: "+rDto.getUserId());
        System.out.println("테스트 11: "+1111);
        if( rDto.getUserId() != principalId ){
            throw new CustomApiException("댓글을 작성할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        System.out.println("테스트 11: "+1111);
        Integer result ;
        try {
            replyRepository.insert(rDto, principalId);
            result = rDto.getId();
            System.out.println("테스트 22: "+2222);
        } catch (Exception e) {
            throw new CustomApiException("댓글 쓰기 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @Transactional
    public void 댓글삭제(int id, int principalId) {
        User admin = userRepository.findById(principalId);
        if ( admin.getRole().equals("ADMIN")){
            try {
                replyRepository.delete(id);
                return;
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new CustomApiException("서버 오류로 댓글삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        Reply reply = replyRepository.findById(id);
        if ( reply == null ){
            throw new CustomApiException("댓글이 존재하지 않습니다.", HttpStatus.FORBIDDEN);
        }
        if ( reply.getUserId() != principalId){
            throw new CustomApiException("자신이 작성한 댓글만 삭제할 수 있습니다.", HttpStatus.FORBIDDEN);
        }
        try {
            replyRepository.delete(id);
        } catch (Exception e) {
            // INTERNAL_SERVER_ERROR 는 무조건 로그를 남겨야한다 !!!!!!!!!!!!!!!!
            //System.out.println("서버에러 : "+ e.getMessage());// 로그의 기능을 한다는거야, 일단 간단하게 남길게 더 정확하세는 아랫줄
            log.error("서버에러 : ", e.getMessage());
            // 로그 서버에도 로그를 보내야함.. 이러한 모든 로직을 AOP 로 구현하면 편하다
            throw new CustomApiException("댓글 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @Transactional
    // public void 댓글삭제(int id, int principalId){
    //     Board board = replyRepository.findById(id);
    //     if ( board == null ){
    //         throw new CustomApiException("해당 게시글이 없습니다.");
    //     }
    //     Board board2 = replyRepository.findById(id);
    //     if ( board2.getUserId() != principalId){
    //         throw new CustomApiException("삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN);
    //     }
    //     int result1 = replyRepository.delete(id);
    //     if ( result1 != 1 ){
    //         throw new CustomApiException("서버에 일시적인 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @Transactional
    // public void 댓글수정(BoardUpdateDto bDto, int id,  int principalId){

    //     String thumbnail = HtmlParser.thumbnailString(bDto.getContent());

    //     Board board = replyRepository.findById(id);
    //     if ( board == null ){
    //         throw new CustomApiException("해당 게시글이 없습니다.");
    //     }
    //     Board board2 = replyRepository.findById(id);
    //     if ( board2.getUserId() != principalId){
    //         throw new CustomApiException("수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
    //     }
    //     int result1 = replyRepository.update(
    //                         bDto.getTitle(),
    //                         bDto.getContent(),
    //                         thumbnail,
    //                         id);
    //     if ( result1 != 1 ){
    //         throw new CustomApiException("서버에 일시적인 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}
