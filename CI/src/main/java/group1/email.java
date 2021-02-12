package group1;

/*
https://netcorecloud.com/tutorials/send-email-in-java-using-gmail-smtp/?hss_channel=tw-3300992070

This code is mainly not writen by group 1 it is mostly copied from the link above.
Group 1 does not claim to have written this code.
*/


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Scanner;
import java.io.File;

public class email {
    public static String readPasswordFile(String fileName){
        try{
            File file = new File(fileName);
            Scanner fr = new Scanner(file);
            String password = fr.nextLine();
            return password;
        }
        catch(java.io.FileNotFoundException e){
            System.out.println("Can't locate password file at: " + fileName);
            return "";
        }
    }

    public static void main(String[] args) {
        final String password = readPasswordFile("password.txt");
        // Recipient's email ID needs to be mentioned.
        String to = "joakim.skoog.joakim@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "group1SoffaKth@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                //konto: group1SoffaKth@gmail.com
                //losen: password.txt
                return new PasswordAuthentication("group1SoffaKth@gmail.com", password);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
