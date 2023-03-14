package shop.mtcoding.blog2.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.dto.love.LoveReqDto.LoveBoardReqDto;
import shop.mtcoding.blog2.model.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class LoveController {
    @Autowired
    private MockMvc mvc;

    private MockHttpSession session;

    @Autowired
    private ObjectMapper om;

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
    public void loveClick_test() throws Exception {
        LoveBoardReqDto rrr = new LoveBoardReqDto();
        rrr.setBoardId(2);
        rrr.setState(1);
        rrr.setUserId(2);
        String test = om.writeValueAsString(rrr);

        ResultActions rs = mvc.perform(post("/love/click").content(test).contentType(MediaType.APPLICATION_JSON_VALUE).session(session));
        System.out.println("테스트 : "+ rs.andReturn().getResponse().getContentAsString());
        
    }
}