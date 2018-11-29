package net.hrms.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.hrms.model.LBS;
import net.hrms.service.FireBaseService;

@Controller
@RequestMapping("/schedules")

public class HrmsSchedularControler {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    FireBaseService fireBaseService;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("HH:mm:ss");

    // @Scheduled(cron = "0 1 0 * * ?")
    @RequestMapping(value = "/pendingApprovalList", method = RequestMethod.GET)
    public void scheduleTaskWithCronExpression() {

        /*
         * logger.info("Cron Task :: Execution Time - {}",
         * dateTimeFormatter.format(LocalDateTime.now()));
         */
        try {
            // Getting Employee json
            
             Map<String, Object> lmsList = fireBaseService
                    .getPendingApprovalList("pending");
            for (Map.Entry<String, Object>
             entry : lmsList.entrySet()) {
             
             Object objectVal = entry.getValue(); Map mapObject =
             (LinkedHashMap) objectVal; String empId =
             mapObject.get("RMID").toString();
            
             
             System.out.println( " Object cal == " +
             objectVal.getClass().getName()); logger.info("lms  json :: {} ",
             lmsList);
             
            }




        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            Date date = new Date();
            LocalDate localDate = date.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();

            // Monthly jobs
            if (month == 1 || month == 6) {
                List<LBS> lbsList = new ArrayList();
                Map<String, Object> data = fireBaseService
                        .getEmployeesLeaveBalanceList();
                SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-mm-dd");

                System.out.println("size of map = " + data.size());

                for (Map.Entry m : data.entrySet()) {
                    LinkedHashMap<String, Object> mapObj = (LinkedHashMap) m
                            .getValue();
                    String DOJ = (String) mapObj.get("EmpDOJ");
                    Date date1 = sd1.parse(DOJ);

                    Date date2 = new Date();
                    System.out.println("date 1= " + date1);
                    System.out.println("date 2= " + date2);

                    int days = (int) ((date2.getTime() - date1.getTime())
                            / (1000 * 60 * 60 * 24));

                    if (days >= 180 && mapObj.get("JobType").toString()
                            .equalsIgnoreCase("Permanent")) {

                    }

                    if ((days >= 365 && mapObj.get("JobType").toString()
                            .equalsIgnoreCase("Permanent")) || month == 1
                            || month == 6) {

                        Double elDays = (Double) mapObj.get("EL");
                    double ELDays = elDays + 7.5;

                        System.out.println("EL Days are == " + ELDays);
                        LBS lbs = new LBS();
                        lbs.setEL(ELDays);
                        if (day == 1) {
                            lbs.setCL(7);
                        } else {
                            lbs.setCL((Integer) mapObj.get("CL"));

                        }
                    lbs.setEmpName(mapObj.get("EmpName").toString());
                    lbs.setEmpId(mapObj.get("EmpId").toString());

                    lbs.setDOJ(mapObj.get("EmpDOJ").toString());
                    lbs.setJobType(mapObj.get("JobType").toString());
                        lbs.setDocumentId(m.getKey().toString());
                        lbsList.add(lbs);


                    }


                }
            fireBaseService.updateLeavesCount(lbsList);

            } else {


            }


                // calculation for update El's to Employee and update into
                // FireBase


                logger.info("Monthly job ended ");
                // yearly jobs

            /*
             * if (month == 1) { logger.info("Yearly job started ");
             * 
             * Map<String, Object> dataLeaveBalancesList = fireBaseService
             * .getEmployeesLeaveBalanceList();
             * 
             * logger.info("Yearly job ended "); }
             */
            // }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        logger.info("Crone Thread : {}", Thread.currentThread().getName());
    }
}
