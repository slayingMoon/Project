package com.tsh.frantishex.eventService.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Configuration
public class EmailServiceImpl  {
//   // @Autowired
//    private JavaMailSender mailSender;
//
//    @Override
//    public void sendSimpleMessage(String toEmail,
//                                  String body,
//                                  String subject) {
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom("erikul1994@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//
//        mailSender.send(message);
//        System.out.println("Mail Send...");
//    }
//
//    @Override
//    public void sendMessageWithAttachment(String toEmail,
//                                          String body,
//                                          String subject,
//                                          String attachment) throws MessagingException {
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//
//        MimeMessageHelper mimeMessageHelper
//                = new MimeMessageHelper(mimeMessage, true);
//
//        mimeMessageHelper.setFrom("erikul1994@gmail.com");
//        mimeMessageHelper.setTo(toEmail);
//        mimeMessageHelper.setText(body);
//        mimeMessageHelper.setSubject(subject);
//
//        FileSystemResource fileSystem
//                = new FileSystemResource(new File(attachment));
//
//        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
//                fileSystem);
//
//        mailSender.send(mimeMessage);
//        System.out.println("Mail Send...");
//
//    }
}
