package ru.job4j.ood.isp.menu;

import java.util.LinkedList;
import java.util.List;

public class MainMenu implements Menu {
    private List<Element> elements = new LinkedList<>();

    @Override
    public void add(Element element) {
        this.elements.add(element);
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        for (Element element : elements) {
            sb.append(element.getName()).append(System.lineSeparator());
            element.outChildren(element, sb);
        }
        System.out.println(sb.toString());
    }
}
