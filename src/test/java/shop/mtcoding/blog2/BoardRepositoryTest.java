package shop.mtcoding.blog2;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.dto.admin.AdminResp.AdminBoardSearchResqDto;
import shop.mtcoding.blog2.model.BoardRepository;

@MybatisTest
public class BoardRepositoryTest {
    
    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void findAllByAdminWithSearch_test() throws Exception{
    
        // int board  = 2;
        ObjectMapper om = new ObjectMapper();
        

        List<AdminBoardSearchResqDto> replyList = boardRepository.findAllByAdminWithSearch("첫번째","","");

        String responseBody = om.writeValueAsString(replyList);
        System.out.println("테스트 : "+ responseBody); 
    }

    // @Test
    // public void 
}
