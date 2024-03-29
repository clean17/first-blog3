package shop.mtcoding.blog2.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog2.dto.board.BoardReq.BoardUpdateDto;
import shop.mtcoding.blog2.dto.board.BoardResp.BoardMainListDto;
import shop.mtcoding.blog2.model.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional(readOnly = true)
public class BoardControllerTest {
    
    @Autowired
    private MockMvc mvc;

    private MockHttpSession session;

    @Autowired
    private ObjectMapper om;


    @BeforeEach
    public void setUp(){
        User mockUser = User.builder()
                        .id(2)
                        .username("ssar")
                        .password("1234")
                        .email("ssar@nate.com")
                        .build();
        session = new MockHttpSession();
        session.setAttribute("principal", mockUser);
    }

    @Test
    @Transactional
    public void boardWrite_test() throws Exception{
        String req = "title=235235&content=235235";

        ResultActions rs = mvc.perform(post("/board/write").content(req)
                                                                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                                                        .session(session));

        rs.andExpect(status().is3xxRedirection());
    }

    @Test
    @Transactional
    public void main_test() throws Exception {
        ResultActions rs = mvc.perform(get("/").session(session));
        ModelAndView mv = rs.andReturn().getModelAndView();
        List<BoardMainListDto> dtos = (mv != null) ? (List<BoardMainListDto>) mv.getModel().get("dtos") : new ArrayList<>();
        System.out.println("테스트 : "+ dtos);
    }


    @Test
    @Transactional
    public void boardDelete_test() throws Exception{
        int id =2;

        ResultActions rs = mvc.perform(delete("/board/"+id+"/delete").session(session));
        rs.andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateForm_test() throws Exception{
        int id = 2;

        ResultActions rs = mvc.perform(get("/board/"+id+"/updateForm").session(session));
        rs.andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void boardUpdate_test() throws Exception{

        BoardUpdateDto dto = new BoardUpdateDto();
        dto.setTitle("안녕");
        dto.setContent("안녕ㅎ");
        String result = om.writeValueAsString(dto);
        System.out.println(result);
        int id =2;

        ResultActions rs = mvc.perform(put("/board/"+id)
        .content(result)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .session(session)
        );
        rs.andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void ddd_test() throws Exception{
    
        String html = "<p>3<img src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEB\">";
        Document d = Jsoup.parse(html);
        Elements els = d.select("img");
        if ( els.size() == 0 ){
            // 임시
        }else{
            String result = els.attr("src");
            System.out.println("테스트 : "+ result);
        }
    }
}
