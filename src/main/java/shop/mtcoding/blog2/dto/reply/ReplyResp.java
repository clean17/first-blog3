package shop.mtcoding.blog2.dto.reply;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class ReplyResp {
    
    @Getter
    @Setter
    public static class ReplyListRespDto {
    // public static class ReplyDetailRespDto{
        private Integer id;
        private String comment;
        private String username;
        private Integer userId;
        private Integer boardId;
        private Timestamp createdAt;
    }
    
    @Getter
    @Setter
    public static class ReplySaveRespDto{
        private Integer id;
        private Integer userId;
        private Integer returnId;
    }
}
