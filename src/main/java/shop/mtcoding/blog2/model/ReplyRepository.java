package shop.mtcoding.blog2.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog2.dto.reply.ReplyResp.ReplyListRespDto;

@Mapper
public interface ReplyRepository {
    public List<Reply> findAll();
    public List<ReplyListRespDto> findByBoardIdWithUser(int boardId);
    // public Reply findByBoardWithUser (int boardId);
    public Reply findById(int id);
    public int insert(
        @Param("comment") String comment,
        @Param("boardId") int boardId,
        @Param("userId") int userId
    );
    // public int insert(Reply reply);
    // @Insert("insert into reply_tb ( comment, board_id, user_id, created_at) values (#{comment}, #{boardId}, #{userId}, now())")
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    // void insert(Reply reply);
    public int deleteById(int id);
    public int updateById(
        @Param("comment") String comment
    );
}
