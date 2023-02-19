package shop.mtcoding.blog2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Love {
    private Integer id;
    private Integer userId;
    private Integer boardId;
    private Integer state;
}
