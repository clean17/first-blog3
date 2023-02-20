package shop.mtcoding.blog2.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardResp {
    
    @Getter
    @Setter
    @ToString
    public static class BoardMainListDto{
        private Integer id;
        private String title;
        private String username;
        private String thumbnail;
        private Integer count;
        private Integer loveId;
        private Integer state;
    }
    
    @Getter
    @Setter
    @ToString
    public static class BoardSearchRespDto{
        private Integer id;
        private String title;
        private String username;
        private String thumbnail;
        private Integer count;
        private Integer loveId;
        private Integer state;
    }

    

    @Getter
    @Setter
    public static class BoardDetailDto{
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private Integer loveId;
        private Integer count;
        private Integer state;
    }

    @Getter
    @Setter
    public static class BoardUpdateRespDto{
        private Integer id;
        private String title;
        private String content;
        private String username;
        private Integer userId;
    }
}
