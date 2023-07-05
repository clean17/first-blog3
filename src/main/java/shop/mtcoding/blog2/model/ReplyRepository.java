package shop.mtcoding.blog2.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog2.dto.admin.AdminResp.AdminReplyRespDto;
import shop.mtcoding.blog2.dto.admin.AdminResp.AdminReplySearchRespDto;
import shop.mtcoding.blog2.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blog2.dto.reply.ReplyResp.ReplyListRespDto;

@Mapper
public interface ReplyRepository {
    public List<Reply> findAll();
    public List<AdminReplyRespDto> findAllByAdmin();
    public List<AdminReplySearchRespDto> findReplyByAdminWithSearch(
        @Param("comment") String comment, 
        @Param("userId") String userId
    );
    public List<ReplyListRespDto> findByBoardIdWithUser(Integer boardId);
    public Reply findById(Integer id);
    public int insert(
        @Param("rDto") ReplySaveReqDto rDto,
        @Param("userId") Integer userId
    ); 
    public int delete(Integer id);
    public int update(String comment);

}
