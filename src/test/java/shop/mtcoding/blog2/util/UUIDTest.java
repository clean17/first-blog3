package shop.mtcoding.blog2.util;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class UUIDTest {
    
    @Test
    public void uuid_test() throws Exception{
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        // uuid 는 해시로 이루어져있음 길이가 같은 결과로 나온다
        // 해시(hash)란 다양한 길이를 가진 데이터를 고정된 길이를 가진 데이터로 매핑(mapping)한 값이다
        // 복호화가 불가능하다 .해시화한 결과를 비교할때 사용함 - 토큰
        // 해시를 사용하는 경우는 정품과 비교할때도 사용한다. 원본과 다르면 해시값이 다르니까
    }
}
