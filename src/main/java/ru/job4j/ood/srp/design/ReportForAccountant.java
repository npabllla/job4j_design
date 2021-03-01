package ru.job4j.ood.srp.design;

import java.util.function.Predicate;

public class ReportForAccountant implements Report {
    private Store store;

    public ReportForAccountant(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(" USD").append(";");
        }
        return text.toString();
    }
}
