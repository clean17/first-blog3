package shop.mtcoding.blog2.repositoryTest;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.dto.love.LoveReqDto.LoveBoardReqDto;
import shop.mtcoding.blog2.model.LoveRepository;

@MybatisTest
@Transactional(readOnly = true)
public class LoveRepositoryTest {
    
    @Autowired
    private LoveRepository loveRepository;

    ObjectMapper om = new ObjectMapper(); 

    @Test
    @Transactional
    public void insert_test() throws Exception {
        LoveBoardReqDto love = new LoveBoardReqDto();
        love.setBoardId(1);
        // love.setId(1);
        love.setState(0);
        // love.setUserId(3);
        String test = om.writeValueAsString(love);
        System.out.println(test);
        loveRepository.insertOrUpdate(love, 3);


    }
}
