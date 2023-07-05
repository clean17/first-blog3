package shop.mtcoding.blog2.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog2.dto.user.UserReq.UserUpdateReqDto;

@Mapper
public interface UserRepository {
        public List<User> findAll();

        public Integer findIdByUsernameWithAdmin(String username);

        public int insert(
                @Param("username") String username,
                @Param("password") String password,
                @Param("email") String email,
                @Param("profile") String profile,
                @Param("role") String role
        );

        public int update(@Param("uDto") UserUpdateReqDto uDto);

        public User findByUsernameAndPassword(
                @Param("username") String username,
                @Param("password") String password)
        ;

        public User findByUsername(String username);

        public User findById(Integer id);

        public int updateProfileById(
                @Param("id") Integer id,
                @Param("username") String username,
                @Param("password") String password,
                @Param("email") String email,
                @Param("profile") String profile,
                @Param("createdAt") Timestamp createdAt
        );

        public int delete(int id);
}
