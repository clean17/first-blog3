package shop.mtcoding.blog2.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import shop.mtcoding.blog2.dto.admin.AdminReq.AdminReqSearchAjaxDto;
import shop.mtcoding.blog2.dto.admin.AdminResp.AdminBoardSearchResqDto;
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
                        .username("ssar")
                        .password("1234")
                        .email("ssar@nate.com")
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
    public void delateUser_test() throws Exception{
        Integer id =null ;
        

        ResultActions rs = mvc.perform(delete("/admin/user/"+id+"/delete")
                    .session(session));

        // String d = (String )rs.andReturn().getModelAndView().getModel().get("msg");
        // System.out.println("테스트 : "+ d);
        // rs.andExpect(status().isOk());
        String a = om.writeValueAsString(rs.andReturn());
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
