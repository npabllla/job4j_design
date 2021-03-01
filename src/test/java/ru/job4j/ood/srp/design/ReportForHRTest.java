package ru.job4j.ood.srp.design;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportForHRTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Alexandr", now, now, 250);
        Employee worker2 = new Employee("Daniil", now, now, 200);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportForHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}