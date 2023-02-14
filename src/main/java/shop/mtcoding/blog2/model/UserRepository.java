package shop.mtcoding.blog2.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    public List<User> findAll();
    public int insertUser(
        @Param("username") String username,
        @Param("password") String password,
        @Param("email") String email
    );
    public User findByUsernameAndPassword(
        @Param("username") String username,
        @Param("password") String password
    );
    public User findByUsername(String username);
    public User findById(int id);
}
