package ru.job4j.serialization.xml;

import java.io.StringReader;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private int capacity;

    @XmlAttribute
    private String brand;

    @XmlAttribute
    private boolean presence;

    private Engine engine;
    private String[] colors;

    public Car() {

    }

    public Car(int capacity, String brand, boolean presence, Engine engine, String[] colors) {
        this.capacity = capacity;
        this.brand = brand;
        this.presence = presence;
        this.engine = engine;
        this.colors = colors;
    }
    

    @Override
    public String toString() {
        return "Car{" 
                + "capacity= " + capacity 
                + ", model=' " + brand + '\''
                + ", presence= " + presence 
                + ", engine= " + engine 
                + ", colors= " + Arrays.toString(colors) 
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final  Car car = new Car(5, "someBrand1", true, new Engine(147.8), new String[]{"Black", "White", "Red"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
