package shop.mtcoding.blog2.dto.admin;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blog2.Util.DateUtil;

public class AdminResp {
    
    @Getter
    @Setter
    public static class AdminBoardRespDto{
        private Integer id;
        private String title;
        private String content;
        private String username;
        private Timestamp createdAt;

        public String getCreatedAtToString() {
            return DateUtil.format(createdAt);
        }
    }

    @Getter
    @Setter
    public static class AdminReplyRespDto{
        private Integer id;
        private String comment;
        private String username;
        private String boardId;
        private Timestamp createdAt;

        public String getCreatedAtToString() {
            return DateUtil.format(createdAt);
        }
    }
}
