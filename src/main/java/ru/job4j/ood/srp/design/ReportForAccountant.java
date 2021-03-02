package ru.job4j.ood.srp.design;

import java.util.function.Predicate;

public class ReportForAccountant implements Report {
    private Store store;
    private double dollarRate;

    public ReportForAccountant(Store store, double dollarRate) {
        this.store = store;
        this.dollarRate = dollarRate;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() / dollarRate).append(" USD").append(";");
        }
        return text.toString();
    }
}
