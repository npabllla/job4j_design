package ru.job4j.ood.srp.design;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportForProgrammerTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForProgrammer(store);
        StringBuilder expect = new StringBuilder().append("<html>")
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
                .append(System.lineSeparator())
                .append(" <p>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";").append("</p>").append(System.lineSeparator())
                .append(" </body>")
                .append(System.lineSeparator())
                .append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}