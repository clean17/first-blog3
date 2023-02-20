package shop.mtcoding.blog2.dto.search;

import lombok.Getter;
import lombok.Setter;

public class SearchReq {

    @Getter
    @Setter
    public static class SearchReqDto {
        private String keyword;
    }
}
