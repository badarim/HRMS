package net.hrms.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.hrms.dto.EmailDTO;
import net.hrms.service.EmailService;

@RestController
@RequestMapping("/emailAPI")

public class LMSEmailController {
    @Autowired
    EmailService emailService;

    @CrossOrigin(origins = "http://localhost:8080")

    @RequestMapping(value = "/applyLeave", method = RequestMethod.POST)
    public void emailforApplyLeave(@RequestBody EmailDTO emailDTO)
            throws MessagingException {
        emailService.sendEmailForApplyLeave(emailDTO);

    }

    @RequestMapping(value = "/leaveStatus", method = RequestMethod.POST)
    public void sendEmailForLeaveStatus(@RequestBody EmailDTO emailDTO)
            throws MessagingException {
        emailService.sendEmailForLeaveStatus(emailDTO);

    }
}
