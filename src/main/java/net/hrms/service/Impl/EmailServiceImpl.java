package net.hrms.service.Impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.hrms.dto.EmailDTO;
import net.hrms.helper.EmailProcessHelper;
import net.hrms.service.EmailService;
import net.hrms.util.EmailFormat;

@Service("emailService")

public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailFormat emailFormat;
    @Autowired
    EmailProcessHelper emailProcessHelper;
    @Value("${hrms.email.user}")
    public String user;

    @Value("${hrms.email.auth}")
    public boolean auth;

    @Value("${hrms.email.pwd}")
    public String pwd;
    @Value("${hrms.email.host}")
    public String host;

    @Override
    public boolean sendEmailForLeaveStatus(EmailDTO emailDTO)
            throws MessagingException {

        Properties props = emailFormat.getProperties();
        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(user));
            InternetAddress toAddress = new InternetAddress();

            // To get the array of addresses
            if(emailDTO.getStatus().equals("Approved")) {
                message.setText(
                        emailFormat
                                .prepareEmailBodyForApprovedLeaves(emailDTO));
                toAddress = new InternetAddress(emailDTO.getEmpEmail());
                message.addRecipient(Message.RecipientType.TO, toAddress);

            } else if (emailDTO.getStatus().equals("Canceled")) {

                message.setText(
                        emailFormat.prepareEmailBodyForCancelLeaves(emailDTO));
                toAddress = new InternetAddress(emailDTO.getRmEmail());
                message.addRecipient(Message.RecipientType.TO, toAddress);

            } else {
                message.setText(
                        emailFormat.prepareEmailBodyForRejectLeaves(emailDTO));
                toAddress = new InternetAddress(emailDTO.getEmpEmail());
                message.addRecipient(Message.RecipientType.TO, toAddress);

            }
            message.setSubject(emailFormat.prepareSubject(emailDTO));

            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, pwd);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("leave status mail sent  sucessfully ");
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean sendEmailForApplyLeave(EmailDTO emailDTO)
            throws MessagingException {

        Properties props = emailFormat.getProperties();
        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(user));
            InternetAddress toAddress = new InternetAddress();

            // To get the array of addresses
            toAddress = new InternetAddress(emailDTO.getRmEmail());
            message.addRecipient(Message.RecipientType.TO,
                    toAddress);
            message.setSubject(emailFormat.prepareSubject(emailDTO));
            message.setText(
                    emailFormat.prepareEmailBodyForApplyLeaves(emailDTO));
            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, pwd);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("apply leave sucessfully ");
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }

        return true;
    }

}
