package shop.mtcoding.blog2.util;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class UUIDTest {
    
    @Test
    public void uuid_test() throws Exception{
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        // uuid - 고정 길이 해시값
        // 사용 목적 - 고유한 식별자 생성
        // 해시 비교를 통해 무결성검사
        // 복호화가 불가능
    }
}
