package shop.mtcoding.blog2.dto.love;

import lombok.Getter;
import lombok.Setter;

public class LoveReqDto {
    
    @Getter
    @Setter
    public static class LoveBoardReqDto{
        // private Integer id;
        private Integer boardId;
        private Integer userId;
        private Integer state;
    }
}
