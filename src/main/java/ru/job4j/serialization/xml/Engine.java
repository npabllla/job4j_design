package ru.job4j.serialization.xml;

import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "engine")
public class Engine {

    @XmlAttribute
    private double power;

    public Engine() {

    }

    public Engine(double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power= " + power + '\''
                + '}';
    }
}
