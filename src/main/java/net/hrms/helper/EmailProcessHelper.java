package net.hrms.helper;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.hrms.util.EmailFormat;

@Component("emailProcessHelper")
public class EmailProcessHelper {

    @Autowired
    EmailFormat emailFormat;

    public MimeMessage getMimeMesageObj() throws MessagingException {
        Properties props = System.getProperties();

        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", "badarimca2003@gmail.com");
        // props.put("mail.smtp.password", "Neeraja1234M");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        /*
         * Session session = Session.getInstance(props, new
         * javax.mail.Authenticator() { protected PasswordAuthentication
         * getPasswordAuthentication() { return new
         * PasswordAuthentication("badarimca2003@gmail.com", "Neeraja1234M"); }
         * });
         */

        MimeMessage message = new MimeMessage(session);


        return message;

    }
}
