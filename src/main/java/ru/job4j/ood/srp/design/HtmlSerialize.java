package ru.job4j.ood.srp.design;

import java.util.List;

public class HtmlSerialize implements Serialize<String, List<Employee>> {
    @Override
    public String serialize(List<Employee> employee) {
        StringBuilder text = new StringBuilder();
        text.append("<html>")
                .append(System.lineSeparator())
                .append(" <head>")
                .append(System.lineSeparator())
                .append("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">")
                .append(System.lineSeparator())
                .append("  <title>Report</title>")
                .append(" </head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append(" <h1>Name; Hired; Fired; Salary;</h1>")
                .append(System.lineSeparator());
        for (Employee emp : employee) {
            text.append(" <p>").append(emp.getName()).append(";")
                    .append(emp.getHired()).append(";")
                    .append(emp.getFired()).append(";")
                    .append(emp.getSalary()).append(";").append("</p>").append(System.lineSeparator());
        }
        text.append(" </body>")
                .append(System.lineSeparator())
                .append("</html>");
        return text.toString();
    }
}
