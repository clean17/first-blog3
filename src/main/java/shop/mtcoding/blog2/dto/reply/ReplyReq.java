package shop.mtcoding.blog2.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyReq {
    
    @Setter
    @Getter
    public static class ReplySaveReqDto{
        private Integer id;
        private String comment;
        private Integer userId;
        private Integer boardId;
    }
}
