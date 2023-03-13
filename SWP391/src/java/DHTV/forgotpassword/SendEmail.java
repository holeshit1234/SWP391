/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.forgotpassword;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author User
 */
public class SendEmail {
     public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }

    //send email to the user email
    public boolean sendEmail(UserDetailsForgetPasswordDTO user) {
        boolean test = false;
        String toEmail = user.getEmail();


        String fromEmail = "";
////        Mail ko có xác thực 2 lớp

      String password = "";
////        MK Mail ko có xác thực 2 lớp

        try {

            // your host email smtp server details
            Properties pr = new Properties();
//            pr.put("mail.smtp.host", "smtp.gmail.com");
//            pr.put("mail.smtp.port", "587");
//            pr.put("mail.smtp.auth", "true");
//            pr.put("mail.smtp.ssl.protocols", "TLSv1.2");
//            pr.put("mail.smtp.starttls.enable", "true");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            //set email subject
            mess.setSubject("User Email Verification");

            //set message text
            mess.setText("Registered successfully. Please verify your account using this code: " + user.getCode());
            //send the message
            Transport.send(mess);

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

}
