package shop.mtcoding.blog2;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.dto.reply.ReplyResp.ReplyListRespDto;
import shop.mtcoding.blog2.model.Reply;
import shop.mtcoding.blog2.model.ReplyRepository;

@MybatisTest
public class ReplyRepositoryTest {
    
    @Autowired
    private ReplyRepository replyRepository;
    @Test
    public void findAllforList_test() throws Exception{
    
        int board  = 2;
        ObjectMapper om = new ObjectMapper();

        List<ReplyListRespDto> replyList = replyRepository.findByBoardIdWithUser(board);

        String responseBody = om.writeValueAsString(replyList);
        System.out.println("테스트 : "+ responseBody); 
    }

    @Test
    public void insert_tset() throws Exception {
        
        int board = 4;
        int userId = 1;
        String str = "ㅋㅋ";
        // ObjectMapper om = new ObjectMapper();
        replyRepository.insert(str, board ,userId);
        Reply rre = new Reply();
        replyRepository.insert(rre);
        System.out.println("테스트 : "+rre.getId());

    }
}
