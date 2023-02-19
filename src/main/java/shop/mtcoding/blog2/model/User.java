package shop.mtcoding.blog2.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blog2.Util.DateUtil;

@Getter
@Setter
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String profile; // 사진의 경로 - /images/default_profile.png
    private String role;
    private Timestamp createdAt;

    public String getCreatedAtToString() {
        return DateUtil.format(createdAt);
    }
}
