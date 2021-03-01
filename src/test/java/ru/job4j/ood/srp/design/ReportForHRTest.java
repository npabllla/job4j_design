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
        store.add(worker);
        Report engine = new ReportForHR(store);
        StringBuilder expect = new StringBuilder()
                .append("{\"name\":").append("\"").append(worker.getName()).append("\",")
                .append("\"salary\":").append((int) worker.getSalary()).append("}");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}