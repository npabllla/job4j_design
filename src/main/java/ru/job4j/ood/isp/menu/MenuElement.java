package ru.job4j.ood.isp.menu;

public interface MenuElement {
    void addChild(Element element);
    void outChildren(Element element, StringBuilder sb);
}
