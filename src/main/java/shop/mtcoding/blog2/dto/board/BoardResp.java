package shop.mtcoding.blog2.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardResp {
    
    @Getter
    @Setter
    public static class BoardMainListDto{
        private int id;
        private String title;
        private String username;
        private String thumbnail;
        private Integer count;
        private Integer state;
    }

    @Getter
    @Setter
    public static class BoardDetailDto{
        private int id;
        private String title;
        private String content;
        private String username;
        private int userId;
    }

    @Getter
    @Setter
    public static class BoardUpdateRespDto{
        private int id;
        private String title;
        private String content;
        private String username;
        private int userId;
    }
}
