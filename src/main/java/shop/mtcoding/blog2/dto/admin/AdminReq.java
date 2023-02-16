package shop.mtcoding.blog2.dto.admin;

import lombok.Getter;
import lombok.Setter;

public class AdminReq {
    
    @Getter
    @Setter
    public static class AdminReqDto{
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class AdminReqDeleteUserDto{
        private Integer id;
    }
    @Getter
    @Setter
    public static class AdminReqDeleteBoardDto{
        private Integer id;
    }
    @Getter
    @Setter
    public static class AdminReqDeleteReplyDto{
        private Integer id;
    }

    @Getter
    @Setter
    public static class AdminReqSearchDto{
        private String title;
        private String content;
        private String username;
    }

    @Getter
    @Setter
    public static class AdminReqSearchAjaxDto{
        private String title;
        private String content;
        private String username;
    }

    @Getter
    @Setter
    public static class AdminReqSearchReplyAjaxDto{
        private String comment;
        private String username;
    }
    
}
