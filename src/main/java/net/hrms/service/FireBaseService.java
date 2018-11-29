package net.hrms.service;

import java.util.List;
import java.util.Map;

import net.hrms.model.LBS;

public interface FireBaseService {

    public Map<String, Object> getPendingApprovalList(String status);

    public Map<String, Object> updateLeavesCount(List<LBS> lbsList);

    public Map<String, Object> getEmployeesLeaveBalanceList();

    // Map<String, Object> updateLeavesCount(String document);

}
