package com.example.bookstore.service;
import com.example.bookstore.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private
    JavaMailSender javaMailSender;

    public void sendSimpleMail(UserData userData)
    {
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("bansodednyanadeo@gmail.com");
            mailMessage.setTo(userData.getEmail());
            mailMessage.setText("Your Registration successfully done just verify it" +
                    "Your Details are: " +
                    "Username:" + userData.getUsername()+
                    "Password:" + userData.getPassword());
            mailMessage.setSubject("Registration successfully done just verify");
            // Sending the mail
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println("Error while Sending Mail");
        }
    }
}
