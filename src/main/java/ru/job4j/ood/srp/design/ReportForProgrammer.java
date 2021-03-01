package ru.job4j.ood.srp.design;

import java.util.function.Predicate;

public class ReportForProgrammer implements Report {
    private Store store;

    public ReportForProgrammer(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        HtmlSerialize htmlSerialize = new HtmlSerialize();
        StringBuilder text = new StringBuilder(htmlSerialize.serialize(store.findBy(filter)));
        return text.toString();
    }
}
