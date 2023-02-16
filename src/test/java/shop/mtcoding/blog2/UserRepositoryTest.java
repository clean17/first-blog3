package shop.mtcoding.blog2;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.dto.reply.ReplyResp.ReplyListRespDto;
import shop.mtcoding.blog2.model.UserRepository;

@MybatisTest
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameWithAdmin_test() throws Exception {

        int board = 2;
        ObjectMapper om = new ObjectMapper();

        Integer te = userRepository.findByUsernameWithAdmin("ss");
        

        // String responseBody = om.writeValueAsString(replyList);
        System.out.println("테스트 : " + te);
    }
}
