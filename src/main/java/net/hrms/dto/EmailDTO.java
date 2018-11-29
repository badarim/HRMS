package net.hrms.dto;

import java.util.Date;

public class EmailDTO {

    private String rmEmail;
    private String empEmail;
    private String reasonForLeave;
    private Date startDate;
    private Integer noOfLeaves;
    private Date endDate;
    private Integer empID;
    private String empName;
    private String rmName;
    private String password;
    private String status;
    private Integer rmId;
    private String comments;





    public String getReasonForLeave() {
        return reasonForLeave;
    }

    public void setReasonForLeave(String reasonForLeave) {
        this.reasonForLeave = reasonForLeave;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRmEmail() {
        return rmEmail;
    }

    public void setRmEmail(String rmEmail) {
        this.rmEmail = rmEmail;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Integer getNoOfLeaves() {
        return noOfLeaves;
    }

    public void setNoOfLeaves(Integer noOfLeaves) {
        this.noOfLeaves = noOfLeaves;
    }

    public Integer getRmId() {
        return rmId;
    }

    public void setRmId(Integer rmId) {
        this.rmId = rmId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
