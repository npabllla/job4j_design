package ru.job4j.ood.isp.menu;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public List<Action> choose(String elementName) {
        Element element = null;
       for (Element element1 : elements) {
           element = search(element1, elementName);
       }
       if (element == null) {
           throw new NoSuchElementException();
       }
       return element.getActions();
    }

    private Element search(Element element, String elementName) {
        if (element.getName().equals(elementName)) {
            return element;
        } else {
            search(element, elementName);
        }
        return null;
    }
}
