package shop.mtcoding.blog2.dto.love;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class LoveReqDto {
    
    @Getter
    @Setter
    @ToString
    public static class LoveBoardReqDto{
        private Integer id;   // 기존 좋아요 좋아허면 그 레코드를 수정하려고 t / f
        private Integer boardId;
        private Integer userId;
        private Integer state;
    }
}
