package org.dc_tech_labs.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.dc_tech_labs.entity.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    public static String officalName = "Dc Tech Labs";
    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    // Method 2
    // To send an email with attachment
    public String sendMailWithAttachment(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    static String getCourseEmailSubject(String type, String action, String partner){
        return switch (type) {
            case "PENDING" -> partner + " " + action;
            case "APPROVED" -> "Your " + officalName + " " + action + " course has been approved";
            case "REJECTED" -> "Your " + officalName + " course submission requires additional information";
            default -> officalName + " course upload approval";
        };
    }

    static String getCourseEmailBody (String type, String action,  String partner, String trainingPartner){
        return switch (type) {
            case "PENDING" -> partner + " from "+  trainingPartner + "completed "+ action +". please review before approving\n" +
                                officalName + "team";
            case "APPROVED" -> "Dear "+officalName + " Grantee,\n"+
                                "Congratulations! Your updates has been approved and are published on "+ officalName+"!";
            case "REJECTED" -> "Dear "+officalName + " Grantee,\n"+
                                "Your submission requires additional information";
            default -> officalName + " course upload approval";
        };
    }
}
