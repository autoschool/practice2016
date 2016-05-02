package ru.qatools.school;

import org.jooq.*;
import org.jooq.impl.DSL;
import ru.qatools.school.apidata.SuggestResp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * Created by omaz on 27.04.16.
 */
public class DbClient {
    private static final String CONNECTION_STRING =
            System.getProperty("db.url", "jdbc:mysql://db.host.ru:3310/db_name");
    private static final String USER = System.getProperty("db.user", "user");
    private static final String PASSWORD = System.getProperty("db.password", "password");

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
                .from(table("City"))
                .where(field("id").equal(id))
                .fetchOne();
        return result.getValue(0, String.class);
    }

    public List<SuggestResp> getSuggestCitiesByNamePart(String namePart) {
        List<SuggestResp> bdSuggests = new ArrayList<>();

        Result<Record> result = create.select()
                .from(table("City"))
                .where(field("name").like("%" + namePart + "%"))
                .fetch();

        for (Record row : result) {
            SuggestResp bdSuggest = new SuggestResp();

            String name = (String) row.getValue("name");

            bdSuggest.setId((long) row.getValue("id"));
            bdSuggest.setUid((long) row.getValue("uid"));
            bdSuggest.setName((String) row.getValue("name"));
            bdSuggest.setCountry((String) row.getValue("country"));

            bdSuggests.add(bdSuggest);
        }

        return bdSuggests;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DbClientException("Не удалось закрыть подключение к " + CONNECTION_STRING, e);
        }
    }
}
