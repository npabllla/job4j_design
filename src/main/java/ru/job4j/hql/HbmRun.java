package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

//            Candidate one = Candidate.of("Alex", 2, 1500);
//            Candidate two = Candidate.of("Nikolay", 1, 750);
//            Candidate three = Candidate.of("Nikita", 15, 3500);
//
//            session.save(one);
//            session.save(two);
//            session.save(three);

            System.out.println(session.createQuery("from Candidate").list());

            Query querySelectByName = session.createQuery("from Candidate where name =: name");
            querySelectByName.setParameter("name", "Alex");
            System.out.println(querySelectByName.getResultList());

            session.createQuery("update Candidate set salary =: salary where id =: id")
                    .setParameter("salary", 2000)
                    .setParameter("id", 1)
                    .executeUpdate();

            session.createQuery("delete from Candidate where id =: id")
                    .setParameter("id", 2)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}