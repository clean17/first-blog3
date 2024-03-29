package shop.mtcoding.blog2.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import shop.mtcoding.blog2.model.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class AdminController {
    
    @Autowired
    private MockMvc mvc;

    private MockHttpSession session;

    @Autowired
    private ObjectMapper om;


    @BeforeEach
    public void setUp(){
        User mockUser = User.builder()
                        .id(1)
                        .username("admin")
                        .password("admin")
                        .email("admin@nate.com")
                        .role("ADMIN")
                        .build();
        session = new MockHttpSession();
        session.setAttribute("principal", mockUser);
    }

    @Test
    public void loginAdmin_test() throws Exception{
        String logindata = "username=admin&password=admin";

        ResultActions rs = mvc.perform(post("/admin/login")
                    .content(logindata)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        rs.andExpect(status().is3xxRedirection());
    }

    @Test
    public void deleteUser_test() throws Exception{
        Integer id = 1 ;
        
        ResultActions rs = mvc.perform(delete("/admin/user/"+id)
                    .session(session));

        rs.andExpect(status().isOk());
        
        // ResponseEntity 결과 json 추출, Object Mapper 필요없음
        String a = rs.andReturn().getResponse().getContentAsString();
        System.out.println(a);
    }

    @Test
    public void searchBoard_test() throws Exception{
        String test = "title=두번째";

        ResultActions rs = mvc.perform(get("/admin/board/search?"+test)
        .session(session));

        String a = rs.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+a);
    }

}
