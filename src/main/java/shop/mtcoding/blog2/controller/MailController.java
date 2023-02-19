package shop.mtcoding.blog2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import shop.mtcoding.blog2.dto.MailDto;
import shop.mtcoding.blog2.service.MailService;

@Controller
@AllArgsConstructor
public class MailController {
    private final MailService mailService;
    
    @PostMapping("/admin/mail")
    public String execMail(MailDto mailDto) {
        mailService.mailSend(mailDto);
        return "redirect:/admin/user";
    }
}


