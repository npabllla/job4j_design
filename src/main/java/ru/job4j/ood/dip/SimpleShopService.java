package ru.job4j.ood.dip;

import java.util.*;
import java.util.logging.Logger;

public class SimpleShopService {
    private ShopStore shopStore;
    private static final Logger LOGGER = Logger.getLogger("Shop logger");

    public SimpleShopService(ShopStore shopStore) {
        this.shopStore = shopStore;
    }

    public boolean createBucket(User user) {
        if (!shopStore.getOrders(user).isEmpty()) {
            return false;
        }
        return shopStore.saveUser(user);
    }

    public boolean createOrder(User user, Order order) {
        Set<Order> orders = shopStore.getOrders(user);
        if (orders.isEmpty()) {
            return false;
        }
        return orders.add(order);
    }

    public boolean addProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderDB.get().add(product);
    }

    private Optional<Order> findOrder(User user, Order order) {
        return shopStore.getOrders(user).stream()
                .filter(o -> o.getId() == order.getId())
                .findFirst();
    }

    public boolean removeProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderDB.get().remove(product.getId());
    }

    public boolean removeOrder(User user, Order order) {
        Set<Order> orders = shopStore.getOrders(user);
        if (orders == null) {
            return false;
        }
        return orders.remove(order);
    }

    public Check payOrder(User user, Order order) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            System.out.println(LOGGER);
            throw new IllegalArgumentException("Invalid order");
        }
        if (orderDB.get().isPayed()) {
            System.out.println(LOGGER);
            throw new IllegalArgumentException("Already payed!");
        }
        orderDB.get().setPayed(true);
        return new Check((int) (System.currentTimeMillis() % 1000_000), "Payed: " + orderDB.get().getId());
    }

}