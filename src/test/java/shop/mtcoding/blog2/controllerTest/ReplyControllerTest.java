package shop.mtcoding.blog2.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

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

import shop.mtcoding.blog2.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blog2.dto.reply.ReplyResp.ReplyListRespDto;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.model.User.UserBuilder;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ReplyControllerTest {
    
    @Autowired
    private MockMvc mvc;

    private MockHttpSession session;

    // @Autowired
    // private ObjectMapper om;


    @BeforeEach
    public void setUp(){
        // User mockUser = new User();
        // mockUser.setId(2);
        // mockUser.setUsername("ssar");
        // mockUser.setPassword("1234");
        // mockUser.setEmail("ssar@nate.com");

        // session = new MockHttpSession();
        // session.setAttribute("principal", mockUser);
        User mockUser = User.builder()
                        .id(2)
                        .username("ㅅㄷㄴㅅ")
                        .password("1234")
                        .email("EF@SEF")
                        .build();

        session = new MockHttpSession();
        session.setAttribute("principal", mockUser);
    }

    @Test
    public void save_test() throws Exception{
        ObjectMapper om = new ObjectMapper();

        ReplySaveReqDto r = new ReplySaveReqDto();
        r.setBoardId(2);
        r.setComment("안녕");   
        r.setUserId(2);
        String test = om.writeValueAsString(r);

        ResultActions rs = mvc.perform(post("/reply/save")
                    .content(test)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .session(session)
                    );

        rs.andExpect(status().isOk());
}
    @Test
    public void boardDetail_test()throws Exception{
        // String insert = "comment=안녕&board=1";
        // int id=1;
        
        ResultActions rs = mvc.perform(get("/board/detail/1")
                    // .content(insert)
                    // .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    // .session(session)
                    );
        
                    // rs.andExpect(status().is3xxRedirection());
                    Map<String, Object> map = rs.andReturn().getModelAndView().getModel();
                    List<ReplyListRespDto> rdo = (List<ReplyListRespDto>) map.get("replyList");
                    assertThat(rdo.get(0).getUsername()).isEqualTo("love");
    }  

    @Test
    public void deleteReply_test() throws Exception{
        int id = 1 ;
        ResultActions rs = mvc.perform(delete("/reply/"+id).session(session));
        rs.andExpect(status().isOk());
        String result = rs.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+ result);
    }
}
