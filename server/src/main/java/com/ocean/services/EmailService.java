package com.ocean.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service("emailService")
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailService(){
        javaMailSender = new JavaMailSenderImpl();
    }

    public String newPassword(){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<10; i++){
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    public String sendNewPassword(String to, String firstName) {
        String pass = newPassword();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oceansocailapp@gmail.com");
        message.setTo(to);
        message.setSubject("Ocean Social Password Reset");
        message.setText("Hello "+ firstName +",\n" +
                "This email is to confirm your password has been reset.\n" +
                "Please find your new password is.\n" +
                "\n"+
                "Password: " + pass +".\n" +
                "\n"+
                "\n"+
                "We recommend changing your password again after login in.\n"+
                "\n"+
                "If you did not request a password change please reach out to our administrators.\n" +
                "at oceansocailapp@gmail.com\n" +
                "Thank you and have a great day!\n" +
                "\n");
        javaMailSender.send(message);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(pass);
    }

    public void welcomeEmail(String to, String firstName){
        System.out.println("EmailService.welcomeEmail");
        SimpleMailMessage welcome = new SimpleMailMessage();
        welcome.setFrom("oceansocailapp@gmail.com");
        welcome.setTo(to);
        welcome.setSubject("Welcome to Ocean Social");
        welcome.setText("Hello "+ firstName +",\n" +
                "This email is to confirm your account has been added\n" +
                " to the Ocean Social Application.\n" +
                "\n"+
                "\n"+
                "To get started please update your profile and then\n" +
                "get to posting in your 'Tide'.\n"+
                "\n"+
                "\n"+
                "Check out other peoples' post and view their profile.\n"+
                "\n"+
                "Leave comments and likes on your friends' post to show them some love. \n"+
                "\n"+
                "\n"+
                "If you have any issues please feel free to reach out to our administrators.\n" +
                "at oceansocailapp@gmail.com\n" +
                "\n"+
                "Thank you and have a great day!");
        javaMailSender.send(welcome);
    }
}
