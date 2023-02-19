package shop.mtcoding.blog2.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shop.mtcoding.blog2.dto.MailDto;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender; 
    private static final String FROM_ADDRESS = "piw940317@gmail.com";  // 보낼 이메일을 설정한다

    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(MailService.FROM_ADDRESS); // 얘를 호출하지 않으면 application.yml에 작성한 username으로 셋팅
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}