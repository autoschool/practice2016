package ru.qatools.school;

import org.junit.rules.ExternalResource;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class DbClientRule extends ExternalResource {

    private DbClient dbClient;

    protected void before() {
        this.dbClient = new DbClient();
    }

    protected void after() {
        dbClient.close();
    }

    public DbClient getDbClient() {
        return dbClient;
    }

}
