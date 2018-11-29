package net.hrms.model;

public class LBS {
    int CL;
    Double EL;
    String DOJ;
    String jobType;
    String documentId;
    String empName;
    String empId;

    public int getCL() {
        return CL;
    }

    public void setCL(int cL) {
        CL = cL;
    }



    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String dOJ) {
        DOJ = dOJ;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Double getEL() {
        return EL;
    }

    public void setEL(Double eL) {
        EL = eL;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}
