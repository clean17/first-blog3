package shop.mtcoding.blog2.Util;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import shop.mtcoding.blog2.exception.CustomApiException;
import shop.mtcoding.blog2.exception.CustomException;

public class CheckValid {
    
    public static void check(Object obj, String msg){
        if (ObjectUtils.isEmpty(obj)){
            throw new CustomException(msg);
        }
    }
    public static void check(Object obj, String msg, HttpStatus status){
        if (ObjectUtils.isEmpty(obj)){
            throw new CustomException(msg, status);
        }
    }
    public static void checkApi(Object obj, String msg){
        if (ObjectUtils.isEmpty(obj)){
            throw new CustomApiException(msg);
        }
    }
    public static void checkApi(Object obj, String msg, HttpStatus status){
        if (ObjectUtils.isEmpty(obj)){
            throw new CustomApiException(msg, status);
        }
    }
    
}
