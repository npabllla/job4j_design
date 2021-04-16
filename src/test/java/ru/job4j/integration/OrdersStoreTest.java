package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void shutdown() throws SQLException {
        pool.getConnection().prepareStatement("DROP TABLE orders").executeUpdate();
    }

    @Test
    public void whenFindAllReturnCollection() {
        OrdersStore store = new OrdersStore(pool);
        List<Order> expected = List.of(new Order(1, "name1", "description1",
                        new Timestamp(System.currentTimeMillis())),
                new Order(2, "name2", "description2",
                    new Timestamp(System.currentTimeMillis())));

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all, is(expected));
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenFindByIdReturnValue() {
        OrdersStore store = new OrdersStore(pool);
        Order expected = new Order(1, "name1", "description1",
                new Timestamp(System.currentTimeMillis()));

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));

        Order order = store.findById(1);

        assertThat(order, is(expected));
    }

    @Test
    public void whenFindByIdReturnNull() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));

        assertNull(store.findById(5));
    }

    @Test
    public void whenFindByNameReturnValue() {
        OrdersStore store = new OrdersStore(pool);
        Order expected = Order.of("name1", "description1");

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));

        Optional<Order> order = store.findByName("name1");

        assertTrue(order.isPresent());
        assertThat(order.get(), is(expected));
    }

    @Test
    public void whenFindByNameReturnNoValue() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));

        Optional<Order> order = store.findByName("name3");

        assertTrue(order.isEmpty());
    }

    @Test
    public void whenUpdateReturnOrder() {
        OrdersStore store = new OrdersStore(pool);

        Order order = new Order(1, "name1", "newDescription",
                new Timestamp(System.currentTimeMillis()));

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));

        assertNotNull(store.update(order));
        assertThat(store.findById(1).getDescription(), is("newDescription"));
    }

    @Test
    public void whenUpdateReturnNull() {
        OrdersStore store = new OrdersStore(pool);

        Order order = new Order(1, "name3", "newDescription",
                new Timestamp(System.currentTimeMillis()));

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));

        assertNull(store.update(order));
    }

}