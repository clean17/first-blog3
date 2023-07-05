package shop.mtcoding.blog2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.blog2.Util.PathUtil;
import shop.mtcoding.blog2.Util.Sha256;
import shop.mtcoding.blog2.dto.ResponseDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserJoinDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserLoginDto;
import shop.mtcoding.blog2.dto.user.UserReq.UserUpdateReqDto;
import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.exception.CustomException;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.model.UserRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    @Transactional
    public void 회원삭제(Integer id, User admin) { 
        try {
            userRepository.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomApiException("서버 오류로 회원삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void 회원가입(UserJoinDto userDto){
        User sameUser = userRepository.findByUsername(userDto.getUsername());
        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }
        userDto.setPassword(Sha256.encode(userDto.getPassword()));
        int result = userRepository.insert(
                userDto.getUsername(), 
                userDto.getPassword(), 
                userDto.getEmail(),
                "/images/default_profile.png",
                "USER"  
                );        
        if (result !=1) {
            throw new CustomException("회원가입에 실패 했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);   
        }
    }

    @Transactional
    public User 로그인(UserLoginDto userDto){
        userDto.setPassword(Sha256.encode(userDto.getPassword()));
        System.out.println("테스트 : "+ userDto.getPassword());
        User principal = userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if ( principal == null ){
            throw new CustomException("아이디 또는 비밀번호가 틀렸습니다.");  
        }
        return principal;
    }
   @Transactional
    public User 프로필사진수정(MultipartFile profile, int pricipalId){
        
        // 1번 사진을 /static/image에 UUID로 변경해서 저장
        String uuidImageName = PathUtil.writeImageFile(profile);
        
        // 2번 저장된 파일의 경로를 DB에 저장
        User userPS = userRepository.findById(pricipalId);
        userPS.setProfile(uuidImageName);

        try {
            userRepository.updateProfileById(userPS.getId(), userPS.getUsername(), userPS.getPassword(), userPS.getEmail(), userPS.getProfile(), userPS.getCreatedAt());
        } catch (Exception e) {
            throw new CustomApiException("프로필 사진 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return userPS;
    }

	@Transactional
    public User 회원수정(UserUpdateReqDto updateReqDto, int principalId){
        if( updateReqDto.getId() != principalId){
            throw new CustomException("본인 정보만 수정 가능합니다.");
        }
        if (updateReqDto.getPassword() == null || updateReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }
        if (updateReqDto.getEmail() == null || updateReqDto.getEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        }
        updateReqDto.setPassword(Sha256.encode(updateReqDto.getPassword()));
        try {
            userRepository.update(updateReqDto);
        } catch (Exception e) {
            throw new CustomException("서버의 일시적인 오류로 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return userRepository.findById(principalId);
    }
        
    @Transactional
    public ResponseDto<?> 중복체크(String username) {
        if (username == null || username.isEmpty()) {
            throw new CustomApiException("username을 입력해주세요");
        }
        User sameUser = userRepository.findByUsername(username);
        if (sameUser != null) {
            throw new CustomApiException("동일한 username이 존재합니다");
        } else {
            return new ResponseDto<>(1, username + " 사용 가능", true);
        }
    }
   
}
