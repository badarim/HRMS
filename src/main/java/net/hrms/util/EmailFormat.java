package net.hrms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.hrms.dto.EmailDTO;

@Component("emailFormat")
public class EmailFormat {
    @Value("${hrms.email.host}")
    public String host;

    @Value("${hrms.email.port}")
    public String port;

    @Value("${hrms.email.user}")
    public String user;

    @Value("${hrms.email.auth}")
    public boolean auth;

    @Value("${hrms.email.pwd}")
    public String pwd;

    static final String SUBJECT = "Pending Leave Approvals Remainder ";
    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-mm-dd");

    static final String PENDING_LEAVES_BODY = String.join(
            System.getProperty("line.separator"),
            "<h1>Dear Manager,</h1>",
            "<p>This email was Remainder for Pending Leave Approvals whose reporting to you.\n "
                    + "Please check and approval those leaves </p>");
    


    public String prepareEmailBodyForApplyLeaves(EmailDTO emailDTO) {
      StringBuilder sb = new StringBuilder();
        System.out.println("start Date == " + emailDTO.getStartDate());
      sb.append("Dear ");
        sb.append(emailDTO.getRmName() + " ," + "\n");
        sb.append("Leave Request has been initiated by  "
                + emailDTO.getEmpName() + " .\n");
        sb.append(
                "From Date: "
                        + getFormatDate(emailDTO.getStartDate().toString())
                        + " \n");
        sb.append("End Date: " + getFormatDate(emailDTO.getEndDate().toString())
                + " \n");
        sb.append("Reason for Leave :  " + emailDTO.getReasonForLeave());
        sb.append("\n");
        sb.append("Kindly perform the required action.  \n ");
        sb.append("\n");
        sb.append("Regards, \n");
        sb.append("LMS Team \n");


        return sb.toString();
    }

    public String prepareEmailBodyForRejectLeaves(EmailDTO emailDTO) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear");
        sb.append(" " + emailDTO.getEmpName() + ", \n");
        sb.append(" Your pending leave request from " + emailDTO.getStartDate()
                + "  To  "
                + emailDTO.getEndDate());
        sb.append(" is rejected  by your Manager.  \n");
        sb.append(" Reason for Rejection: ");
        sb.append(emailDTO.getComments());
        sb.append("\n");
        sb.append("Regards,  \n");
        sb.append("LMS Team   \n");

        return sb.toString();
    }

    public String prepareEmailBodyForApprovedLeaves(EmailDTO emailDTO) {

        StringBuilder sb = new StringBuilder();
        sb.append("Dear");
        sb.append(" " + emailDTO.getEmpName() + ", \n");
        sb.append(" Your pending leave request from " + emailDTO.getStartDate()
                + "  To  "
                + emailDTO.getEndDate());
        sb.append(" is approved by your Manager.  \n");
        sb.append("\n");
        sb.append("Regards,  \n ");
        sb.append("LMS Team   \n ");

        return sb.toString();
    }

    public String prepareEmailBodyForCancelLeaves(EmailDTO emailDTO)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ");
        sb.append(emailDTO.getRmName() + " ," + "\n");
        sb.append("Leave Request has been Canceled  by  "
                + emailDTO.getEmpName() + " .\n");
        sb.append("Regards, \n");
        sb.append("LMS Team \n");

        return sb.toString();
    }

    public Properties getProperties() {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        return props;


    }

    public String prepareSubject(EmailDTO emailDTO) {
        if (emailDTO.getStatus().equalsIgnoreCase("Pending")) {
            return "Leave Request from " + emailDTO.getEmpName();
        } else if (emailDTO.getStatus().equalsIgnoreCase("Approved")) {
            
            return "Leave approved From  "
                    + getFormatDate(emailDTO.getStartDate().toString()) + " To"
                    + getFormatDate(emailDTO.getEndDate().toString());
        } else {
            return " Leave Rejected  From "
                    + getFormatDate(emailDTO.getStartDate().toString()) + " To"
                    + getFormatDate(emailDTO.getEndDate().toString());
        }
    }

    private String getFormatDate(String date) {
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date dateConv = null;
        try {
            dateConv = formatter.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateConv);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
                + "-" + cal.get(Calendar.DATE);
    }
}
