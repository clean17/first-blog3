package shop.mtcoding.blog2.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyReq {
    
    @Setter
    @Getter
    public static class ReplySaveReqDto{
        private String comment;
        private Integer boardId;
        private Integer returnId;
    }
}
