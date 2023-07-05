package shop.mtcoding.blog2.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blog2.Util.DateUtil;

@Builder
@Getter
@Setter
public class User {
    private final Integer id;
    private final String username;
    private final String password;
    private final String email;
    private String profile; // 사진의 경로 - /images/default_profile.png, uuid 붙여서 고유값으로 변환함, final x
    private final String role;
    private final Timestamp createdAt;

    public String getCreatedAtToString() {
        return DateUtil.format(createdAt);
    }

}
