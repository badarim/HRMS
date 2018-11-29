package net.hrms.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.hrms.model.LBS;
import net.hrms.service.FireBaseService;

@Service("fireBaseService")

public class FireBaseServiceImpl implements FireBaseService {
    RestTemplate restTemplate = new RestTemplate();
    @Value("${hrms.firebase.lbs}")
    public String lbsURL;
    @Override
    public Map<String, Object> getPendingApprovalList(String status) {
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "https://fir-demo-e52b2.firebaseio.com/LMS.json?orderBy=\"Status\"&equalTo=\"pending\"",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });
        return response.getBody() != null ? response.getBody() : null;
    }

    public Map<String, Object> updateLeavesCount(String document) {
        // TODO Auto-generated method stub


        final String uri = "https://fir-demo-e52b2.firebaseio.com/LBS/-LS3RbjO1htsJfIA1weA/.json";

        Map<String, String> params = new HashMap<String, String>();
        // params.put("id", "-LRpKs_ux-jNotfXLyCW");

        // LBS lbs = new LBS();
        // lbs.setCL(10);
        // lbs.setEL(11);

        // restTemplate.exchange(uri, HttpMethod.PUT, lbs, Void.class);

        // restTemplate.patchForObject(uri, lbs, responseType)
        // restTemplate.put(uri, lbs, params);

        RestTemplate restTemplate = new RestTemplate();

        // String requestJson = "{\"CL\":\"10\"}";
        String requestJson = "{\"CL\":8,\"EL\":8,\"EmpDOJ\":\"2017-10-01\",\"EmpId\":\"201\",\"EmpName\":\"BadariNarayana\",\"JobType\":\"Permanent\"}";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,
                headers);
        System.out.println(" Json Data = " + entity);
        
        restTemplate.put(uri, entity);
        
        // String answer = restTemplate.putForObject(url, entity, String.class);
        // System.out.println(answer);

        return null;
    }

    @Override
    public Map<String, Object> getEmployeesLeaveBalanceList() {
        Map<String, Object> responseData = null;
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "https://fir-demo-e52b2.firebaseio.com/LBS.json",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });
        return response.getBody() != null ? response.getBody() : null;


        
        
    }

    @Override
    public Map<String, Object> updateLeavesCount(List<LBS> lbsList) {

        final String uri = "https://fir-demo-e52b2.firebaseio.com/LBS/-LS3RbjO1htsJfIA1weA/.json";
        RestTemplate restTemplate = new RestTemplate();
        // String requestJson = "{\"CL\":\"10\"}";
         String requestJson =
                "{\"CL\":10,\"EL\":8,\"EmpDOJ\":\"2017-10-01\",\"EmpId\":201,\"EmpName\":\"BadariNarayana\",\"JobType\":\"Permanent\"}";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("static json =" + requestJson);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // restTemplate.put(uri, entity);

        // StringBuilder requestJsonBuilder = new StringBuilder();
        // String requestJson =
        // "{\"CL\":\"10\",\"EL\":\"20\",\"EmpDOJ:\":\"2018-10-01\"}";

        lbsList.forEach(lbsObj -> {
            StringBuilder requestJsonBuilder = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            sb.append(lbsURL);

            sb.append(lbsObj.getDocumentId() + "/.json");
            requestJsonBuilder.append("{");
            requestJsonBuilder.append("\"EL\"");


            requestJsonBuilder.append(":" + lbsObj.getEL());

            requestJsonBuilder.append(",");

            requestJsonBuilder.append("\"CL\"");
            requestJsonBuilder.append(":" + lbsObj.getCL());
            requestJsonBuilder.append(",");
            requestJsonBuilder.append("\"EmpDOJ\"");
            requestJsonBuilder.append(":");
            requestJsonBuilder.append("\"" + lbsObj.getDOJ() + "\"");
            requestJsonBuilder.append(",");
            requestJsonBuilder.append("\"JobType\"");
            requestJsonBuilder.append(":");

            requestJsonBuilder.append("\"" + lbsObj.getJobType() + "\"");
            requestJsonBuilder.append(",");
            requestJsonBuilder.append("\"EmpId\"");
            requestJsonBuilder.append(":");

            requestJsonBuilder.append(lbsObj.getEmpId());
            requestJsonBuilder.append(",");
            requestJsonBuilder.append("\"EmpName\"");
            requestJsonBuilder.append(":");

            requestJsonBuilder.append("\"" + lbsObj.getEmpName() + "\"");

            requestJsonBuilder.append("}");
            System.out.println(
                    "JSon Dynamic  == " + requestJsonBuilder.toString());
            System.out.println("Static URI  = " + uri);

            System.out.println("URL = " + sb.toString());
            System.out.println("JSon == " + requestJson.toString());
            HttpEntity<String> entity = new HttpEntity<String>(
                    requestJsonBuilder.toString(),
                    headers);
            System.out.println(" Json Data after = " + entity);

            restTemplate.put(sb.toString(), entity);

        });


        return null;
    
    }



}
