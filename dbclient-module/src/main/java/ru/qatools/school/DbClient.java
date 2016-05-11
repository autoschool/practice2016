package ru.qatools.school;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ru.qatools.school.api.suggest.SuggestCity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * @author omaz
 * @author gladnik (Nikolai Gladkov)
 */
public class DbClient {
    private static final String CONNECTION_STRING =
            System.getProperty("db.url", "jdbc:mysql://db.weather.lanwen.ru:3306/weather");
    private static final String USER = System.getProperty("db.user", "");
    private static final String PASSWORD = System.getProperty("db.password", "");

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

    public List<SuggestCity> getSuggestCitiesForQuery(String query) {
        List<SuggestCity> suggestCities = create
                .select()
                .from(table("City"))
                .where(field("name").like("%" + query + "%"))
                .fetchInto(SuggestCity.class);
        return suggestCities;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DbClientException("Не удалось закрыть подключение к " + CONNECTION_STRING, e);
        }
    }
}
