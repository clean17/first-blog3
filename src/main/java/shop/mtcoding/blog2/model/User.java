package shop.mtcoding.blog2.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import shop.mtcoding.blog2.Util.DateUtil;

@Builder
@Getter
@Setter
// @RequiredArgsConstructor
public class User {
    private final Integer id;
    private final String username;
    private final String password;
    private final String email;
    private String profile; // 사진의 경로 - /images/default_profile.png
    private final String role;
    private final Timestamp createdAt;

    public String getCreatedAtToString() {
        return DateUtil.format(createdAt);
    }

}
