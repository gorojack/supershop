package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.dto.MailDto;

public interface MailService {

    void sendMail(MailDto mailDto);
}
