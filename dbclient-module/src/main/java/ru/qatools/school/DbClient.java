package ru.qatools.school;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static ru.qatools.school.DbPreconditions.*;

/**
 * Created by omaz on 27.04.16.
 * edited by arrumm
 */
public class DbClient {
    private static final String CONNECTION_STRING =
            System.getProperty("db.url", DB_URL);
    private static final String USER = System.getProperty("db.user", DB_USER);
    private static final String PASSWORD = System.getProperty("db.password", DB_PASSWORD);

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

    public String getCityById(Integer id) {
        Record1 result = create.select(field("name"))
                .from(table("table_name"))
                .where(field("id").equal(id))
                .fetchOne();
        return result.getValue(0, String.class);
    }

    public List<String> getCitiesNameContains(String name) {
        List<String> cities = create.select()
                .from(table("City"))
                .where(field("name").like("%" + name + "%"))
                .limit(100).fetch().getValues("name", String.class);
        return cities;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DbClientException("Не удалось закрыть подключение к " + CONNECTION_STRING, e);
        }
    }
}
