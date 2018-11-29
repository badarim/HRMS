package net.hrms.service;

import javax.mail.MessagingException;

import net.hrms.dto.EmailDTO;

public interface EmailService {

    public boolean sendEmailForApplyLeave(EmailDTO emailDTO)
            throws MessagingException;

    public boolean sendEmailForLeaveStatus(EmailDTO emailDTO)
            throws MessagingException;


}
