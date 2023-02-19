package shop.mtcoding.blog2.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog2.dto.love.LoveReqDto.LoveBoardReqDto;
import shop.mtcoding.blog2.dto.love.LoveRespDto.LoveBoardRespDto;

@Mapper
public interface LoveRepository {
    public List<Love> findAll();
    public Love findByIdAndBoardId(LoveBoardReqDto lDto);

    public LoveBoardRespDto findByBoardIdAndUserId(
        @Param("boardId") Integer boardId,
        @Param("userId") Integer userId 
    );
    public int insertOrUpdate(
        @Param("lDto") LoveBoardReqDto lDto,
        @Param("userId") int userId
    );
    
}
