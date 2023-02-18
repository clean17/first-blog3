package shop.mtcoding.blog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.blog2.dto.love.LoveReqDto.LoveBoardReqDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.model.Board;
import shop.mtcoding.blog2.model.BoardRepository;
import shop.mtcoding.blog2.model.LoveRepository;

@Slf4j
@Transactional(readOnly = true)
@Service
public class LoveService {
    @Autowired
    private LoveRepository loveRepository;

    @Autowired
    private BoardRepository boardRepository;
    
    @Transactional
    public void 클릭하기(LoveBoardReqDto lDto, int principalId) {
        // 유저 아이디 세션 아이디 비교
        if ( principalId != lDto.getUserId()){
            throw new CustomApiException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        Board board = boardRepository.findById(lDto.getBoardId());
        if ( board == null ){
            throw new CustomApiException("게시글이 존재하지 않습니다.");
        }

        // 입력전 state 값 바꾸기
        try {
            loveRepository.insertOrUpdate(lDto, principalId);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomApiException("서버에 일시적인 오류가 발생했습니다.");
        }
    }
}
    