package shop.mtcoding.blog2.service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shop.mtcoding.blog2.dto.MailDto;
import shop.mtcoding.blog2.exception.CustomException;
import shop.mtcoding.blog2.model.User;

@Service
@AllArgsConstructor
public class MailService {
    @Autowired
    private HttpSession session;

    private JavaMailSender mailSender; 
    private static final String FROM_ADDRESS = "piw940317@gmail.com";  // 보낼 이메일을 설정한다

    public void mailSend(MailDto mailDto) throws MessagingException{
        User principal = (User)session.getAttribute("principal");
        if( !principal.getRole().equals("ADMIN")){
            throw new CustomException("관리자 권한이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        try {
            // MailHandler mailHandler = new MailHandler(mailSender);
            
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}