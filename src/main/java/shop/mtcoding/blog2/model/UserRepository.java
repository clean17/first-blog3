package shop.mtcoding.blog2.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    public List<User> findAll();
    public Integer findByUsernameWithAdmin(String username);

    public int insertUser(
            @Param("username") String username,
            @Param("password") String password,
            @Param("email") String email,
            @Param("profile") String profile,
            @Param("role") String role
            );
            

    public User findByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password);

    public User findByUsername(String username);

    public User findById(int id);

    public int updateById(
            @Param("id") int id,
            @Param("username") String username,
            @Param("password") String password,
            @Param("email") String email,
            @Param("profile") String profile,
            @Param("createdAt") Timestamp createdAt);
    public int delete(int id);
}
