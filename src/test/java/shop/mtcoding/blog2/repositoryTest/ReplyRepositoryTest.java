package shop.mtcoding.blog2.repositoryTest;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.dto.admin.AdminResp.AdminReplySearchRespDto;
import shop.mtcoding.blog2.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blog2.dto.reply.ReplyResp.ReplyListRespDto;
import shop.mtcoding.blog2.model.ReplyRepository;
import shop.mtcoding.blog2.model.User;

@MybatisTest
public class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private MockHttpSession session;

    @BeforeEach
    public void setUp(){
        User mockUser = User.builder()
                        .id(1)
                        .username("ssar")
                        .password("1234")
                        .email("ssar@nate.com")
                        .build();
        session = new MockHttpSession();
        session.setAttribute("principal", mockUser);
    }
    
    @Test
    public void findAllforList_test() throws Exception{
    
        int board  = 2;
        ObjectMapper om = new ObjectMapper();

        List<ReplyListRespDto> replyList = replyRepository.findByBoardIdWithUser(board);

        String responseBody = om.writeValueAsString(replyList);
        System.out.println("테스트 : "+ responseBody); 
    }

    @Test
    public void findReplyByAdminWithSearch_test() throws Exception{
    
        // int board  = 2;
        // ObjectMapper om = new ObjectMapper();

        List<AdminReplySearchRespDto> replyList = replyRepository.findReplyByAdminWithSearch("ㅋㅋ","1");
        System.out.println("테스트 : "+replyList.size());
        // String responseBody = om.writeValueAsString(replyList);
        // System.out.println("테스트 : "+ responseBody); 
    }


    @Test
    public void insert_test() throws Exception{
        ReplySaveReqDto r = new ReplySaveReqDto();
        r.setComment("zzz");
        r.setBoardId(2);

        int result = replyRepository.insert(r, 1);
        System.out.println("테스트 : "+result);
    }
}
