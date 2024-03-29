package com.gestionstock.gestionstock.entity;
import java.util.Properties;
import jakarta.mail.Authenticator;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.AddressException;


public class Mail {

    public Mail() {
    }

    public static void main(String[] args) { // throws MessagingException {
            Mail app = new Mail();
            String title = "[APP] Welcome to the Jakarta Mail Demo Application";
            app.displayTitle(title);

            final String to = "s.abdelmalek@lprs.fr";
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
            }
            catch(AuthenticationFailedException exception) {
                System.out.println("[EXCEPTION] AuthenticationFailedException" + exception);
            }
            catch(AddressException exception) {
                System.out.println("[EXCEPTION] AddressException" + exception);
            }
            catch(MessagingException exception) {
                System.out.println("[EXCEPTION] MessagingException" + exception);
            }
        }
        public void displayTitle(String title) {
            int length = title.length();
            System.out.print("[APP] ");
            for(int i = 0; i < length; ++i) {
                System.out.print("-");
            }
            System.out.println();
            System.out.println(title);
            System.out.print("[APP] ");
            for(int i = 0; i < length; ++i) {
                System.out.print("-");
            }
            System.out.println();
    }
}


