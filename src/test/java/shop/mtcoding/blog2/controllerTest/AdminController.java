package shop.mtcoding.blog2.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("admin");
        mockUser.setPassword("admin");
        mockUser.setRole("ADMIN");
        mockUser.setEmail("admin@nate.com");

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
        // AdminReqSearchDto a = new AdminReqSearchDto();
        // a.setTitle("첫번째");
        // a.setContent(null);
        // a.setUsername(null);

        String test = "title=&content=내용&username=";

        ResultActions rs = mvc.perform(post("/admin/board/search")
        .content(test)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .session(session));
        // System.out.println("테스트 : "+);
        // Map<String, Object> a = (Map<String, Object>)  rs.andReturn().getModelAndView().getModel();
        // List<AdminBoardSearchRestDto> b = (List<AdminBoardSearchRestDto>) a.get("boardList");
        // int te = b.size();
        List<AdminBoardSearchResqDto> rr = (List<AdminBoardSearchResqDto>) rs.andReturn().getModelAndView().getModel().get("boardList");
        System.out.println("테스트123 : "+rr);
    }

}
