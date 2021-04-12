package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            List<Car> cars = List.of(Car.of("Model 1"),
                    Car.of("Model 2"),
                    Car.of("Model 3"),
                    Car.of("Model 4"),
                    Car.of("Model 5"));
            for (Car car : cars) {
                session.save(car);
            }

            Mark mark = Mark.of("Main Mark");
            for (int i = 1; i <= 5; i++) {
                mark.addCar(session.load(Car.class, i));
            }

            session.save(mark);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}