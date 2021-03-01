package ru.job4j.ood.srp.design;

import org.json.JSONObject;

public class JsonSerialize implements Serialize<JSONObject, Employee> {
    @Override
    public JSONObject serialize(Employee employee) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", employee.getName());
        jsonObject.put("salary", employee.getSalary());
        return jsonObject;
    }
}
