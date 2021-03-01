package ru.job4j.ood.srp.design;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportForHR  implements Report {
    private Store store;

    public ReportForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sorted = store.findBy(filter).stream()
                .sorted((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()))
                .collect(Collectors.toList());
        for (Employee employee : sorted) {
            JsonSerialize jsonSerialize = new JsonSerialize();
            text.append(jsonSerialize.serialize(employee).toString());
        }
        return text.toString();
    }

}
