package ru.qatools.school;

import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * Created by omaz on 27.04.16.
 */
public class DbClient {
    private static final String CONNECTION_STRING =
            System.getProperty("db.url", "jdbc:mysql://db.weather.lanwen.ru:3306/weather");
    private static final String USER = System.getProperty("db.user", "autoschool");
    private static final String PASSWORD = System.getProperty("db.password", "ya2016");;

    private Connection connection;
    private DSLContext create;

    public DbClient() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DbClientException("Не удалось установить подключение к " + CONNECTION_STRING, e);
        }
        create = DSL.using(connection, SQLDialect.MYSQL);
    }

    public String getCityById(String str) {
        Record1 result = create.select(field("name"))
                .from(table("City"))
                .where(field("name").like(str))
                .fetchOne();
         return result.getValue(0, String.class);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DbClientException("Не удалось закрыть подключение к " + CONNECTION_STRING, e);
        }
    }
}
