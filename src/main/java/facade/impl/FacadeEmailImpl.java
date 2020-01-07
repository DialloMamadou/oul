package facade.impl;

import facade.facadeEmail;
import notification.Notification;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

public class FacadeEmailImpl implements facadeEmail {




    private static String USER_NAME = "malikabdallah75019";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "Selamwait04"; // GMail password
    private static String RECIPIENT = "malikabdallah75019@gmail.com";




    public FacadeEmailImpl(String from,String pass,String to,String subject,String body) {
        String []too=new String[1];
        too[0]=to;
        sendFromGMail(from, pass, too, subject, body,new File(""));

    }

    public FacadeEmailImpl() {
    }


    public  void test() {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";

        sendFromGMail(from, pass, to, subject, body, new File(""));
    }

    public   void sendFromGMail(String from, String pass, String[] to, String subject, String body, File file) {
        System.out.println("to to "+to[0]);
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", "malikabdallah75019");
        props.put("mail.smtp.password", "Selamwait04");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
           /* message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();*/







            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(from);


            InternetAddress[] toAddress = new InternetAddress[to.length];

            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < to.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }


          //  msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));



            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // Set the email msg text.
            MimeBodyPart messagePart = new MimeBodyPart();
            messagePart.setText(body);

            // Set the email attachment file
            FileDataSource fileDataSource = new FileDataSource(file);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setDataHandler(new DataHandler(fileDataSource));
            attachmentPart.setFileName(fileDataSource.getName());

            // Create Multipart E-Mail.
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);
            multipart.addBodyPart(attachmentPart);

            msg.setContent(multipart);

            // Send the msg. Don't forget to set the username and password
            // to authenticate to the mail server.
            Transport.send(msg, from, pass);










            Notification.affichageSucces("succes","l email a ete envoyee avec success");















            /*message.setFrom(new InternetAddress(from));

            InternetAddress[] toAddress = new InternetAddress[to.length];
            toAddress[0]=new InternetAddress(to[0]);
            System.out.println("adresse choisi "+toAddress[0]);
            message.addRecipient(Message.RecipientType.TO, toAddress[0]);

            System.out.println("to adresse "+toAddress[0]);


            message.setSubject("Testing Subject");

            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("This is message body");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = "/home/manisha/file.txt";
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            if(file==null){
                Notification.affichageEchec("message null","file nul");
            }
            messageBodyPart.setFileName(file.getName());
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

*/



        }
        catch (AddressException ae) {
            System.out.println("");
            Notification.affichageEchec("echec envoie message","l adresse n a pas ete trouve");

        }
        catch (MessagingException me) {
            me.printStackTrace();
            Notification.affichageEchec("ERREUR LIE AU SERVEUR","LE SERVEUR A RENCONTRE UN PROBLEME MERCI DE REEASAYER ULTERIEUREMENT");

        }
    }

}
