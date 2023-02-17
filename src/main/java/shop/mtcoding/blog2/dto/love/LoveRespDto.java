package shop.mtcoding.blog2.dto.love;

import lombok.Getter;
import lombok.Setter;

public class LoveRespDto {
    
    @Getter
    @Setter
    public static class LoveBoardRespDto{
        // private int id;
        private int count;
        private int state;
    }

    @Getter
    @Setter
    public static class LoveReplyRespDto{
        // private int id;
        private int count;
        private int state;
    }
}
