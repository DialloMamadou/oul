package facade;

public interface facadeEmail {

    void sendFromGMail(String from, String pass, String[] to, String subject, String body);

}
