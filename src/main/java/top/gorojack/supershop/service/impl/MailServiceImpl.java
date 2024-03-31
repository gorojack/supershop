package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.pojo.dto.MailDto;
import top.gorojack.supershop.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.email}")
    private String email;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    @Resource
    private JavaMailSender mailSender;

    @Override
    public void sendMail(MailDto mailDto) {
        if (email == null || host == null || port == null || username == null || password == null) {
            throw new RuntimeException("邮箱配置异常");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username + "<" + email + ">");
        message.setTo(mailDto.getTos());
        message.setSubject(mailDto.getSubject());
        message.setText(mailDto.getContent());

        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
