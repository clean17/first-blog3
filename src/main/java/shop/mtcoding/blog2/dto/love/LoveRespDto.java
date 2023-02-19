package shop.mtcoding.blog2.dto.love;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class LoveRespDto {
    
    @Getter
    @Setter
    @ToString
    public static class LoveBoardRespDto{
        private Integer id;
        private Integer count;
        private Integer state;
    }

    @Getter
    @Setter
    public static class LoveReplyRespDto{
        private Integer id;
        private Integer count;
        private Integer state;
    }
}
