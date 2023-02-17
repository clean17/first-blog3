package shop.mtcoding.blog2.controllerTest;

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

    ObjectMapper om = new ObjectMapper();

    @Test
    public void findBoardByAdminWithSearch_test() throws Exception{
        List<AdminBoardSearchResqDto> boardList = boardRepository.findBoardByAdminWithSearch("","두번째","");
        String tes = om.writeValueAsString(boardList);
        System.out.println("테스트 : "+ tes);
    }
}
