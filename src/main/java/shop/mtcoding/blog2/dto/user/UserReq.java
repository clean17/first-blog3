package shop.mtcoding.blog2.dto.user;

import lombok.Getter;
import lombok.Setter;


public class UserReq {

    @Getter
    @Setter
    public static class UserJoinDto{
        private String username;
        private String password;
        private String email;
    }

    @Getter
    @Setter
    public static class UserLoginDto{
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class UserUpdateReqDto{
    	private Integer id;
        private String password;
        private String email;
    }
    
}
