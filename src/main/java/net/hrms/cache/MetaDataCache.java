package net.hrms.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetaDataCache {
    public static Map<String, String> emailMap = new ConcurrentHashMap<>();

    public String getEmail(String empId) {
        String email = emailMap.get(empId);
        if (email == null) {

            emailMap.put("", "");
            return email;

        } else {
            return email;
        }

    }

}
