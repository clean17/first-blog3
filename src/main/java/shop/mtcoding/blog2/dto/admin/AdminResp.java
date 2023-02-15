package shop.mtcoding.blog2.dto.admin;


import java.sql.Timestamp;

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

    @Getter
    @Setter
    public static class AdminReplyRespDto{
        private Integer id;
        private String comment;
        private String username;
        private String boardId;
        private Timestamp createdAt;
    }
}
