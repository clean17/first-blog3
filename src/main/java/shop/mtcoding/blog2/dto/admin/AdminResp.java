package shop.mtcoding.blog2.dto.admin;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class AdminResp {
    
    @Getter
    @Setter
    public static class AdminBoardRespDto{
        private Integer id;
        private String title;
        private String content;
        private String username;
        private Timestamp createdAt;
    }
}
