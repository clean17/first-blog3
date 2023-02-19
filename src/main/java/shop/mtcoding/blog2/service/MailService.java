package shop.mtcoding.blog2.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shop.mtcoding.blog2.Util.MailHandler;
import shop.mtcoding.blog2.dto.MailDto;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender; 
    private static final String FROM_ADDRESS = "piw940317@gmail.com";  // 보낼 이메일을 설정한다

    public void mailSend(MailDto mailDto) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            
            // 받는 사람
           mailHandler.setTo(mailDto.getAddress());
            // 보내는 사람
           mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
           mailHandler.setSubject(mailDto.getTitle());
            // HTML Layout
            String htmlContent = "<p>" + mailDto.getMessage() +"<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);
            // 첨부 파일
           mailHandler.setAttach("newTest.txt", "static/originTest.txt");
            // 이미지 삽입
           mailHandler.setInline("sample-img", "static/sample1.jpg");

            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}