package com.example.a31b;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// import java.net.Authenticator;

public class JavaMailUtil{

    public static void sendMail(String recepient) throws Exception {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "31b.emergency@gmail.com";
        String password = "C02emergencyapp";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });


        Message message = prepareMessage(session, myAccountEmail, recepient);


        Transport.send(message);
        System.out.println("Message sent succesfully");
    }


    public static Message prepareMessage(Session session, String myAccountEmail, String recepient) {

        try {

            Random random = new Random();
            int code = 0;
            code = random.nextInt(8999)+1000;

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Verification Code");
            message.setText(String.valueOf(code));
            return message;
        } catch (Exception ex) {
            Logger.getLogger(popUpWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
