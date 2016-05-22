package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import ru.qatools.school.DbClient;

/**
 * @author ava1on
 */
public class DbClientRule extends ExternalResource {

    private DbClient dbClient;

    public void before(){
        this.dbClient = new DbClient();
    }

    public void after(){
        dbClient.close();
    }

    public DbClient getDbClient(){
        return dbClient;
    }
}
