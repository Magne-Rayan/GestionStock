package com.gestionstock.gestionstock.entity;
import java.io.File;
import java.util.Properties;

import com.itextpdf.text.Document;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.util.StreamProvider;



public class Mail {
    public void sendMail(Document doc) {

        Mail app = new Mail();
        String title = "[APP] Welcome to the Jakarta Mail Demo Application";
        app.displayTitle(title);

        final String to = "r.magne@lprs.fr";
        final String from = "gestionstocklprs@gmail.com";
        final String username = "gestionstocklprs@gmail.com";
        final String password = "ruxwkerigiayopnz";

        final String host = "smtp.gmail.com";
        final int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        System.out.println("[APP] " + auth);
        System.out.println("[APP] To: " + to);
        System.out.println("[APP] From: " + from);
        System.out.println("[APP] Host: " + host);

        Session session = Session.getInstance(props, auth);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Objet");
            message.setText("Test message");
            Transport.send(message);
            System.out.println("[APP] Email sent successfully");
        } catch (AuthenticationFailedException exception) {
            System.out.println("[EXCEPTION] AuthenticationFailedException" + exception);
        } catch (AddressException exception) {
            System.out.println("[EXCEPTION] AddressException" + exception);
        } catch (MessagingException exception) {
            System.out.println("[EXCEPTION] MessagingException" + exception);
        }
    }

    public void displayTitle(String title) {
        int length = title.length();
        System.out.print("[APP] ");
        for (int i = 0; i < length; ++i) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(title);
        System.out.print("[APP] ");
        for (int i = 0; i < length; ++i) {
            System.out.print("-");
        }
        System.out.println();
    }
    public void jakartaEmail(){
        Mail app = new Mail();
        String title = "Demande de prix";
        app.displayTitle(title);

        //provide recipient's email ID
        String to = "r.magne@lprs.fr";
        //provide sender's email ID
        String from = "gestionstocklprs@gmail.com";
        //provide Mailtrap's username
        final String username = "gestionstocklprs@gmail.com";
        //provide Mailtrap's password
        final String password = "ruxwkerigiayopnz";
        //provide Mailtrap's host address
        String host = "smtp.gmail.com";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(props, authenticator);
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            //set email subject field
            message.setSubject("Here comes an attachment!");
            //create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            //set the actual message
            messageBodyPart.setText("Please find the attachment sent using Jakarta Mail");
            //create an instance of multipart object
            Multipart multipart = new MimeMultipart();
            //set the first text message part
            multipart.addBodyPart(messageBodyPart);
            //set the second part, which is the attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\magne\\IdeaProjects\\GestionStock\\Pdf\\DemandeDePrix.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            //send the entire message parts
            message.setContent(multipart);
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}




