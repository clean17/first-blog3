package shop.mtcoding.blog2.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UserReq {

    @Getter
    @Setter
    @Builder
    public static class UserJoinDto{
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        private String username;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        private String email;
    }

    @Getter
    @Setter
    public static class UserLoginDto{
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        private String username;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        // @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;
    }

    @Getter
    @Setter
    public static class UserUpdateReqDto{
    	private Integer id;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        // @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        private String email;
    }
    
}
