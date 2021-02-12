package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        try (InputStream in = new FileInputStream("./data/app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists %s;",
                        tableName
                );
                statement.execute(sql);
                System.out.println(getScheme(tableName));
            }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "drop table if exists %s;",
                    tableName
            );
            statement.execute(sql);
            System.out.println(getScheme(tableName));
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table if exists %s add column %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
            System.out.println(getScheme(tableName));
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table if exists %s drop column %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql);
            System.out.println(getScheme(tableName));
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table if exists %s rename column %s to %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
            System.out.println(getScheme(tableName));
        }
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}