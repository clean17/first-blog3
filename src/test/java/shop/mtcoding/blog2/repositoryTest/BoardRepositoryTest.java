package shop.mtcoding.blog2.repositoryTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.dto.admin.AdminResp.AdminBoardSearchResqDto;
import shop.mtcoding.blog2.dto.board.BoardResp.BoardMainListDto;
import shop.mtcoding.blog2.model.BoardRepository;

@MybatisTest
@Transactional(readOnly = true)
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // 첫번째 방법
    // @MockBean
    // private ObjectMapper om; // Mockito라이브러리와 함께 동작을 정의해아 함

    // 두번재 방법
    @TestConfiguration
    static class ObjectMapperTestContextConfiguration {
  
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }

    @Autowired
    private ObjectMapper om;

    // 세번째 방법 - 직접 인스턴스 생성
    // ObjectMapper om = new ObjectMapper();

    // 실패하는 방법 - @RequiredArgsConstructor 사용
    // private final ObjectMapper om; // @RequiredArgsConstructor 가 final 이 붙은 필드를 자동으로 생성자에 넣어서 초기화 해준다.
    // 단위 테스트로 스프링 컨텍스트가 ObjectMapper를 제공하지 않을 경우 ParameterResolutionException 발생한다.

    @Test
    @Transactional
    public void findBoardByAdminWithSearch_test() throws Exception {
        List<AdminBoardSearchResqDto> boardList = boardRepository.findBoardByAdminWithSearch("", "두번째", "");
        String tes = om.writeValueAsString(boardList);
        System.out.println("테스트 : "+ tes);
    }

    @Test
    @Transactional
    public void findAllforList_test() throws Exception {
        // Integer id = null ;
        List<BoardMainListDto> btos = boardRepository.findAllforList(null);
        System.out.println("테스트 : " + btos);

    }

}
