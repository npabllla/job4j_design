package ru.job4j.ood.isp.menu;

import java.util.LinkedList;
import java.util.List;

public class Element implements MenuElement {
    private String name;
    private List<Action> actions;
    private List<Element> children = new LinkedList<>();

    public Element(String name, List<Action> actions) {
        this.name = name;
        this.actions = actions;
    }

    public String getName() {
        return this.name;
    }

    public List<Element> getChildren() {
        return children;
    }

    public List<Action> getActions() {
        return actions;
    }

    @Override
    public void addChild(Element element) {
        children.add(element);
    }

    @Override
    public void outChildren(Element element, StringBuilder sb) {
        if (element.getChildren().size() != 0) {
            for (Element el : element.getChildren()) {
                sb.append(el.getName()).append(System.lineSeparator());
                outChildren(el, sb);
            }
        }
    }
}
