package shop.mtcoding.blog2.repositoryTest;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.model.UserRepository;

@MybatisTest
@Transactional(readOnly = true)
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void findByUsernameWithAdmin_test() throws Exception {

        int board = 2;
        ObjectMapper om = new ObjectMapper();

        Integer te = userRepository.findByUsernameWithAdmin("ss");

        // String responseBody = om.writeValueAsString(replyList);
        System.out.println("테스트 : " + te);
    }

    @Test
    @Transactional
    public void findByUsernameWithAdmi2n_test() throws Exception {

        int board = 2;
        ObjectMapper om = new ObjectMapper();

        Integer te = userRepository.findByUsernameWithAdmin("ss");

        // String responseBody = om.writeValueAsString(replyList);
        System.out.println("테스트 : " + te);
    }
}
